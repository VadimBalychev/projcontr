package project.control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.control.mapper.TaskMapper;
import project.control.model.Task;
import project.control.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    public List<Task> getAllTasks() {
        return repository.findAll().stream().map(mapper::toTask).toList();
    }

    public Task createTask(Task task) {
        return mapper.toTask(repository.save(mapper.toTaskEntity(task)));
    }

    public Task updateTask(Long taskId, Task task) {
        if (repository.findById(taskId).isPresent()) {
            task.setId(taskId);
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