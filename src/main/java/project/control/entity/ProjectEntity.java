package project.control.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "is_finished")
    private Boolean isFinished;

    @OneToMany(mappedBy = "project")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<TaskEntity> tasks;
}