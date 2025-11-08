package com.devs.hackaton.security.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.exception.TokenNuloException;
import com.devs.hackaton.repository.UserRepository;
import com.devs.hackaton.security.config.SecurityConfig;
import com.devs.hackaton.security.userdetails.UserDetailsImpl;
import com.devs.hackaton.exception.UsuarioNaoEcontradoPeloEmailDoTokenException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Verifica se o endpoint requer autenticação antes de processar a requisição
        if (checkIfEndpointIsNotPublic(request)) {
            try {
                String token = extractTokenFromRequest(request); // Recupera o token do cabeçalho Authorization da requisição
                if (token != null) {
                    // Obtém o assunto (neste caso, o email de usuário) do token
                    String subject = jwtTokenService.validateToken(token);
                    if (subject != null) {
                        // Busca o usuário pelo email (que é o assunto do token)
                        User user = userRepository.findByEmail(subject).orElseThrow(UsuarioNaoEcontradoPeloEmailDoTokenException::new);
                        // Cria um UserDetails com o usuário encontrado
                        UserDetailsImpl userDetails = new UserDetailsImpl(user);
                        // Cria um objeto de autenticação do Spring Security
                        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        // Define o objeto de autenticação no contexto de segurança do Spring Security
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        // Token inválido ou expirado
                        enviarErroJson(response, HttpStatus.UNAUTHORIZED, "Token inválido ou expirado");
                        return;
                    }
                } else {
                    throw new TokenNuloException();
                }
            } catch (TokenNuloException e) {
                enviarErroJson(response, HttpStatus.UNAUTHORIZED, "Token de acesso requerido");
                return;
            } catch (UsuarioNaoEcontradoPeloEmailDoTokenException e) {
                enviarErroJson(response, HttpStatus.UNAUTHORIZED, "Usuário não encontrado pelo email vinculado ao token");
                return;
            } catch (JWTVerificationException e) {
                enviarErroJson(response, HttpStatus.UNAUTHORIZED, "Token inválido ou expirado");
                return;
            } catch (Exception e) {
                enviarErroJson(response, HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response); // Continua o processamento da requisição}
    }


    // Recupera o token do cabeçalho Authorization da requisição
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        // Verifica padrões com wildcard
        for (String publicEndpoint : SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED) {
            if (publicEndpoint.endsWith("/**")) {
                String basePattern = publicEndpoint.substring(0, publicEndpoint.length() - 3);
                if (requestURI.startsWith(basePattern)) {
                    return false; // É um endpoint público
                }
            } else if (requestURI.equals(publicEndpoint)) {
                return false; // É um endpoint público
            }
        }

        return true; // Requer autenticação
    }

    private void enviarErroJson(HttpServletResponse response, HttpStatus status, String mensagem) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(mensagem));
        response.getWriter().flush();
    }
}
