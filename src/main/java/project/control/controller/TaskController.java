package project.control.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.control.model.Task;
import project.control.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;
    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long taskId) {
        return service.getTaskById(taskId);
    }
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable(name = "id") Long taskId,
                           @RequestBody Task task) {
        return service.updateTask(taskId, task);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long taskId) {
        service.deleteTask(taskId);
    }
}