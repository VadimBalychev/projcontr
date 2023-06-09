package project.control.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.control.model.enums.Phase;
import project.control.model.enums.Priority;

import java.util.Date;

@Entity
@Table(name = "task")
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "finish_date")
    private Date finishDate;
    @Column(name = "phase")
    private Phase phase;
    @Column(name = "name")
    private String name;
    @Column(name = "priority")
    private Priority priority;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;
}