package org.example.jesabackend.dto;

import org.example.jesabackend.model.Project;
import org.example.jesabackend.model.Punch;
import org.example.jesabackend.repository.ProjectRepository;
import org.example.jesabackend.service.PunchService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

public class PunchDTO {
    private Long id;
    private String title;
    private String description;
    private String status;           // as String for simplicity
    private Long projectId;          // just project ID, no full entity
    private String createdByName;    // e.g. user’s name or email
    private String modifiedByName;   // optional
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PunchDTO(Long id, String title, String description, String status,
                    Long projectId, String createdByName, String modifiedByName,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.createdByName = createdByName;
        this.modifiedByName = modifiedByName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    // Getters and setters (or use Lombok @Data to auto-generate)

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getCreatedByName() { return createdByName; }
    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }

    public String getModifiedByName() { return modifiedByName; }
    public void setModifiedByName(String modifiedByName) { this.modifiedByName = modifiedByName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
