package org.example.jesabackend.service;

import org.example.jesabackend.model.Project;
import org.example.jesabackend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> updateProject(Long id, Project project) {
        return projectRepository.findById(id).map(existing -> {
            existing.setCode(project.getCode());
            existing.setName(project.getName());
            existing.setDescription(project.getDescription());
            existing.setCreatedBy(project.getCreatedBy());
            return projectRepository.save(existing);
        });
    }

    public boolean deleteProject(Long id) {
        return projectRepository.findById(id).map(project -> {
            projectRepository.delete(project);
            return true;
        }).orElse(false);
    }
}