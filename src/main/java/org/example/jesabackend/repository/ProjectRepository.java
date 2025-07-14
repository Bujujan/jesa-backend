package org.example.jesabackend.repository;

import org.example.jesabackend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProjectRepository extends JpaRepository<Project, Long> {
}