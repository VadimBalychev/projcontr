package project.control.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import project.control.repository.ProjectRepository;
import project.control.repository.TaskRepository;

@Controller
@RequiredArgsConstructor
public class DELETECONTROLLER {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    @DeleteMapping("/deleteall")
    public void deleteAll() {
        projectRepository.deleteAll();
        taskRepository.deleteAll();
    }
}
