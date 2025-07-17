package org.example.jesabackend.controller;

import org.example.jesabackend.dto.PunchDTO;
import org.example.jesabackend.dto.PunchStatsDTO;
import org.example.jesabackend.model.Project;
import org.example.jesabackend.model.Punch;
import org.example.jesabackend.repository.ProjectRepository;
import org.example.jesabackend.service.PunchService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/punches")
public class PunchController {

    private final org.example.jesabackend.service.PunchService punchService;
    private final ProjectRepository projectRepository;

    public PunchController(PunchService punchService, ProjectRepository projectRepository) {
        this.punchService = punchService;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Punch> getAllPunches() {
        return punchService.getAllPunches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Punch> getPunchById(@PathVariable Long id) {
        return punchService.getPunchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<Punch> updatePunch(@PathVariable Long id, @RequestBody Punch punch) {
        return punchService.updatePunch(id, punch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePunch(@PathVariable Long id) {
        if (punchService.deletePunch(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<PunchStatsDTO> getPunchStats() {
        return ResponseEntity.ok(punchService.getPunchStats());
    }

    @GetMapping("/dto")
    public List<PunchDTO> getAllPunchesDTO() {
        return punchService.getAllPunchesDTO();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, "application/json;charset=UTF-8" })
    public Punch createPunch(@RequestBody PunchDTO punchDTO) {
        Project project = projectRepository.findById(punchDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Punch punch = new Punch();
        punch.setTitle(punchDTO.getTitle());
        punch.setDescription(punchDTO.getDescription());
        punch.setProject(project);

        return punchService.createPunch(punch);
    }
}