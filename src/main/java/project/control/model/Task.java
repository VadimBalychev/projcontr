package project.control.model;

import lombok.Data;
import project.control.model.enums.Phase;
import project.control.model.enums.Priority;

import java.util.Date;

@Data
public class Task {
    private Long id;
    private Date createDate;
    private Date finishDate;
    private String description;
    private Phase phase;
    private String name;
    private Long projectId;
    private Priority priority;
}
