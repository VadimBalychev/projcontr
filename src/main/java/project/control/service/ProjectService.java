package project.control.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.control.mapper.ProjectMapper;
import project.control.model.Project;
import project.control.repository.ProjectRepository;

import java.util.List;

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
        createLog(project);
        return mapper.toProject(repository.save(mapper.toProjectEntity(project)));
    }

    public Project updateProject(Long projectId, Project project) {
        if (repository.findById(projectId).isPresent()) {
            project.setId(projectId);
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

    public static void createLog(Project project) {
        log.info("\n\nПопытка добавить проект с параметрами: " + '\n' +
        "id: " + project.getId() + '\n' +
        "name: " + project.getName() + '\n' +
        "description: " + project.getDescription() + '\n' + '\n');
    }
}