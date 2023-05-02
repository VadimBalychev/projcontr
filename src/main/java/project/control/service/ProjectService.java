package project.control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.control.entity.ProjectEntity;
import project.control.mapper.ProjectMapper;
import project.control.model.Project;
import project.control.repository.ProjectRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    public List<Project> getAllProjects() {
        return repository.findAll().stream().map(mapper::toProject).toList();
    }

    public Project getProjectsById(Long projectId) {
        Optional<Project> project = repository.findById(projectId).map(mapper::toProject);
        if (project.isPresent()) {
            return project.get();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public Project createProject(Project project) {
        project.setCreateDate(Date.from(Instant.now()));
        return mapper.toProject(repository.save(mapper.toProjectEntity(project)));
    }

    public Project updateProject(Long projectId, Project project) {
        Optional<ProjectEntity> entity = repository.findById(projectId);
        if (entity.isPresent()) {
            project.setId(projectId);
            project.setCreateDate(entity.get().getCreateDate());
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