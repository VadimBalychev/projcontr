package project.control.mapper;

import project.control.entity.TaskEntity;
import project.control.model.Task;

public interface TaskMapper {
    Task toTask(TaskEntity entity);

    TaskEntity toTaskEntity(Task task);
}