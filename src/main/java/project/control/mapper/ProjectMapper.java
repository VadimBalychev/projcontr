package project.control.mapper;

import project.control.entity.ProjectEntity;
import project.control.model.Project;

public interface ProjectMapper {
    Project toProject(ProjectEntity entity);

    ProjectEntity toProjectEntity(Project project);
}