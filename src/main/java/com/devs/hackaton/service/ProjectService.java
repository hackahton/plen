package com.devs.hackaton.service;

import com.devs.hackaton.entity.Project; import com.devs.hackaton.entity.User; import com.devs.hackaton.enums.Role; import com.devs.hackaton.repository.ProjectRepository; import com.devs.hackaton.repository.UserRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.HttpStatus; import org.springframework.stereotype.Service; import org.springframework.web.server.ResponseStatusException;

import java.util.List; import java.util.Optional; import java.util.UUID;

@Service public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project saveProject(Project project, Role userRole) {

        if (project.getId() == null) {
            if (userRole == Role.COLABORADOR) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Collaborators cannot create new projects. Only GESTOR or SUPERVISOR.");
            }
            return projectRepository.save(project);
        }

        Optional<Project> existingProjectOpt = projectRepository.findById(project.getId());

        if (existingProjectOpt.isPresent()) {

            if (userRole != Role.SUPERVISOR) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Only the Supervisor can alter project data and status.");
            }

            return projectRepository.save(project);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Project with ID " + project.getId() + " not found.");
        }
    }

    public Project associateUser(UUID projectId, UUID userId, Role userRole) {
        if (userRole != Role.SUPERVISOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Only the Supervisor can associate users with a project.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        if (project.getUsers().contains(user)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already associated with this project.");
        }

        project.getUsers().add(user);
        user.getProjects().add(project);

        return projectRepository.save(project);
    }

    public Project transferProjectResponsibility(UUID projectId, UUID newManagerId, Role requestUserRole) {
        if (requestUserRole != Role.SUPERVISOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Only the SUPERVISOR can transfer project responsibility.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found."));

        User newManager = userRepository.findById(newManagerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "New Manager/Supervisor not found."));

        if (newManager.getRole() != Role.GESTOR && newManager.getRole() != Role.SUPERVISOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The new responsible must be a GESTOR or SUPERVISOR.");
        }

        if (!project.getUsers().contains(newManager)) {
            project.getUsers().add(newManager);
            newManager.getProjects().add(project);
        }

        return projectRepository.save(project);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findProjectById(UUID id) {
        return projectRepository.findById(id);
    }

    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }
}