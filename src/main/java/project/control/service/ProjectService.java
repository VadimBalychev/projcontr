package project.control.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.control.entity.ProjectEntity;
import project.control.entity.TaskEntity;
import project.control.mapper.ProjectMapper;
import project.control.model.Project;
import project.control.repository.ProjectRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    public List<Project> getAllProjects() {
        return repository.findAll().stream().map(mapper::toProject).toList();
    }

    public Project createProject(Project project) {
        project.setCreateDate(Date.from(Instant.now()));
        createLog(project);
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
            log.info("\n\nПри попытке обновления проекта, проект с id = " + projectId + " не обнаружен\n\n");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void deleteProject(Long projectId) {
        if (repository.findById(projectId).isPresent()) {
            repository.deleteById(projectId);
        }
        else {
            log.info("\n\nПри попытке удаления проекта, проект с id = " + projectId + " не обнаружен\n\n");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void createLog(Project project) {
        log.info("\n\nПопытка добавить проект с параметрами: " + '\n' +
        "id: " + project.getId() + '\n' +
        "name: " + project.getName() + '\n' +
        "description: " + project.getDescription() + '\n' +
        "createTime: " + project.getCreateDate() + '\n' +
        "isFinished: " + project.getIsFinished() + '\n' + '\n');
    }
}