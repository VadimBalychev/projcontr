package project.control.mapper.impl;

import org.springframework.stereotype.Component;
import project.control.entity.TaskEntity;
import project.control.mapper.TaskMapper;
import project.control.model.Task;
import project.control.repository.ProjectRepository;

@Component
public class TaskMapperImpl implements TaskMapper {
    ProjectRepository repository;
    @Override
    public Task toTask(TaskEntity entity) {
        Task task = new Task();
        task.setName(entity.getName());
        task.setPhase(entity.getPhase());
        task.setPriority(entity.getPriority());
        task.setCreateTime(entity.getCreateTime());
        task.setFinishTime(entity.getFinishTime());
        task.setProjectName(entity.getProject().getName());
        task.setId(entity.getId());
        task.setDescription(entity.getDescription());
        return task;
    }

    @Override
    public TaskEntity toTaskEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(task.getName());
        taskEntity.setPhase(task.getPhase());
        taskEntity.setPriority(task.getPriority());
        taskEntity.setCreateTime(task.getCreateTime());
        taskEntity.setFinishTime(task.getFinishTime());
        taskEntity.setProject(repository.findByName(task.getName()));
        taskEntity.setId(task.getId());
        taskEntity.setDescription(task.getDescription());
        return taskEntity;
    }
}