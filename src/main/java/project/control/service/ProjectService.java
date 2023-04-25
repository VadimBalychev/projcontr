package project.control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.control.mapper.ProjectMapper;
import project.control.model.Project;
import project.control.repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    public List<Project> getAllProjects() {
        return repository.findAll().stream().map(mapper::toProject).toList();
    }

    public Project createProject(Project project) {
        return mapper.toProject(repository.save(mapper.toProjectEntity(project)));
    }

    public Project updateProject(Long projectId, Project project) {
        if (repository.findById(projectId).isPresent()) {
            project.setId(projectId);
            return mapper.toProject(repository.save(mapper.toProjectEntity(project)));
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void deleteProject(Long projectId) {
        if (repository.findById(projectId).isPresent()) {
            repository.deleteById(projectId);
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }
}