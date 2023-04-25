package project.control.mapper.impl;

import org.springframework.stereotype.Component;
import project.control.entity.ProjectEntity;
import project.control.entity.TaskEntity;
import project.control.mapper.ProjectMapper;
import project.control.model.Project;
import project.control.model.Task;

import java.util.List;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public Project toProject(ProjectEntity entity) {
        Project project = new Project();
        List<Task> taskList = entity.getTasks().stream().map(x -> {
            Task task = new Task();
            task.setName(x.getName());
            task.setPhase(x.getPhase());
            task.setPriority(x.getPriority());
            task.setCreateTime(x.getCreateTime());
            task.setFinishTime(x.getFinishTime());
            task.setProjectName(x.getProject().getName());
            task.setId(x.getId());
            task.setDescription(x.getDescription());
            return task;
        }).toList();

        project.setId(entity.getId());
        project.setName(entity.getName());
        project.setTasks(taskList);

        return project;
    }

    @Override
    public ProjectEntity toProjectEntity(Project project) {
        ProjectEntity projectEntity = new ProjectEntity();

        List<TaskEntity> taskEntityList = project.getTasks().stream().map(x -> {
            TaskEntity task = new TaskEntity();
            task.setName(x.getName());
            task.setPhase(x.getPhase());
            task.setPriority(x.getPriority());
            task.setCreateTime(x.getCreateTime());
            task.setFinishTime(x.getFinishTime());
            task.setProject(projectEntity);
            task.setId(x.getId());
            task.setDescription(x.getDescription());
            return task;
        }).toList();

        projectEntity.setId(project.getId());
        projectEntity.setName(project.getName());
        projectEntity.setTasks(taskEntityList);

        return projectEntity;
    }
}