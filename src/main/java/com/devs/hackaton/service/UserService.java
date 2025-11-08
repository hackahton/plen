package com.devs.hackaton.service;

import com.devs.hackaton.dto.UserDTOs.request.UpdatePassword;
import com.devs.hackaton.dto.UserDTOs.request.UpdateUserTag;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.dto.UserDTOs.response.FindUserResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Tag;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.dto.UserDTOs.request.LoginUserRequest;
import com.devs.hackaton.dto.UserDTOs.response.LoginUserReponse;
import com.devs.hackaton.enums.Role;
import com.devs.hackaton.exception.LoginUsuarioRequestNuloException;
import com.devs.hackaton.exception.User.AccessDeniedException;
import com.devs.hackaton.exception.User.UserNotFoundException;
import com.devs.hackaton.mapper.UserMapper;
import com.devs.hackaton.repository.CompanyRepository;
import com.devs.hackaton.repository.UserRepository;
import com.devs.hackaton.security.auth.JwtTokenService;
import com.devs.hackaton.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final TagService tagService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;


    public CreateUserResponse createUser(UserRequest request, User logged) {
        if(logged.getRole() == Role.COLABORADOR){
            throw new AccessDeniedException();
        }
        User user = UserMapper.toEntity(request);
        Company company = companyRepository.findById(request.companyId()).orElseThrow(RuntimeException::new);
        user.setCompany(company);
        user.setStatus(Company_User_Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }


    public CreateUserResponse createUserADM(UserRequest request) {
        User user = UserMapper.toEntity(request);
        Company company = companyRepository.findById(request.companyId()).orElseThrow(RuntimeException::new);
        user.setCompany(company);
        user.setStatus(Company_User_Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    public List<CreateUserResponse> findAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public FindUserResponse findUserById(UUID id) {
        log.info("Fetching user with id: {}", id);
        User user = findUserEntityById(id);
        return UserMapper.toFindResponse(user);
    }

    public User findUserEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }


    public void toggleUserStatus(UUID id) {
        User user = findUserEntityById(id);
        user.setStatus(toggleStatus(user.getStatus()));

        userRepository.save(user);
    }

    private Company_User_Status toggleStatus(Company_User_Status status) {
        return status.equals(Company_User_Status.ACTIVE)
                ? Company_User_Status.INACTIVE
                : Company_User_Status.ACTIVE;
    }

    public void UpdatePassword(UUID id, UpdatePassword request) {
        User user = findUserEntityById(id);
        user.setPassword(request.password());
        userRepository.save(user);
    }

    public void updateUserTag(UUID id, UpdateUserTag tag){
        User user = findUserEntityById(id);
        Tag tagEntity = tagService.findTagEntityById(tag.tagId());

        user.getTags().add(tagEntity);
        userRepository.save(user);
    }

    public void removeUserTag(UUID id, UpdateUserTag tag){
        User user = findUserEntityById(id);
        Tag tagEntity = tagService.findTagEntityById(tag.tagId());

        user.getTags().remove(tagEntity);
        userRepository.save(user);
    }


    public User findUserEntityByStatusAndId(UUID id, Company_User_Status status){
        return userRepository.findUserByIdAndStatus(id, status);
    }

    public LoginUserReponse loginUsuario(LoginUserRequest request) {
        log.info("Verificando se o request para efetuar o login do usuário está nulo...");
        if (Objects.isNull(request)) {
            throw new LoginUsuarioRequestNuloException();
        }
        log.info("Request verificado e não está nulo.");
        try {// Cria um objeto de autenticação com o email e a senha do usuário
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(request.email(), request.password());

            // Autentica o usuário com as credenciais fornecidas
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            // Obtém o objeto UserDetails do usuário autenticado
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Gera um token JWT para o usuário autenticado
            String token = jwtTokenService.generateToken(userDetails);

            log.info("Login de usuário efetuado com sucesso.");
            log.info("Token gerado: {}", token);
            return new LoginUserReponse(token);

        } catch (BadCredentialsException e) {
            log.error("Tentativa de login com credenciais inválidas para email: {}", request.email());
            throw new BadCredentialsException("Email ou senha incorretos.");
        }
    }
}
