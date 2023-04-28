package project.control.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.control.entity.TaskEntity;
import project.control.mapper.TaskMapper;
import project.control.model.Task;
import project.control.repository.TaskRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    public List<Task> getAllTasks() {
        return repository.findAll().stream().map(mapper::toTask).toList();
    }

    public Task createTask(Task task) {
        task.setCreateDate(Date.from(Instant.now()));
        createLog(task);
        return mapper.toTask(repository.save(mapper.toTaskEntity(task)));
    }

    public Task updateTask(Long taskId, Task task) {
        Optional<TaskEntity> entity = repository.findById(taskId);
        if (entity.isPresent()) {
            task.setId(taskId);
            task.setCreateDate(entity.get().getCreateDate());
            return mapper.toTask(repository.save(mapper.toTaskEntity(task)));
        }
        else {
            log.info("\n\nПри попытке обновления задачи, задачи с id = " + taskId + " не обнаружено\n\n");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void deleteTask(Long taskId) {
        if (repository.findById(taskId).isPresent()) {
            repository.deleteById(taskId);
        }
        else {
            log.info("\n\nПри попытке удаления задачи, задачи с id = " + taskId + " не обнаружено\n\n");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void createLog(Task task) {
        log.info("\n\nПопытка добавить задачу с параметрами: " + '\n' +
        "id: " + task.getId() + '\n' +
        "name: " + task.getName() + '\n' +
        "description: " + task.getDescription() + '\n' +
        "phase: " + task.getPhase() + '\n' +
        "priority: " + task.getPriority() + '\n' +
        "createTime: " + task.getCreateDate() + '\n' +
        "finishTime: " + task.getFinishDate() + '\n' + '\n');
    }
}