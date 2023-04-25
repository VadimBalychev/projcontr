package project.control.model;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private Long id;
    private String name;
    private List<Task> tasks;
}
