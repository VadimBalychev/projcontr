package project.control.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.control.model.Project;
import project.control.service.ProjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    @GetMapping
    public List<Project> getAllProjects() {
        return service.getAllProjects();
    }
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return service.createProject(project);
    }
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable(name = "id") Long projectId,
                                 @RequestBody Project project) {
        return service.updateProject(projectId, project);
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable(name = "id") Long projectId) {
        service.deleteProject(projectId);
    }
}