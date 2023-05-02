package project.control.service;

import lombok.RequiredArgsConstructor;
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
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    public List<Task> getAllTasks() {
        return repository.findAll().stream().map(mapper::toTask).toList();
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> task = repository.findById(taskId).map(mapper::toTask);
        if (task.isPresent()) {
            return task.get();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public Task createTask(Task task) {
        task.setCreateDate(Date.from(Instant.now()));
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
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    public void deleteTask(Long taskId) {
        if (repository.findById(taskId).isPresent()) {
            repository.deleteById(taskId);
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }
}