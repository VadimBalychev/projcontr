package project.control.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Project {
    private Long id;
    private String name;
    private String description;
    private Date createDate;
    private Boolean isFinished;
    private List<Task> tasks;
}
