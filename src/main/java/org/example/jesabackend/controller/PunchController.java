package org.example.jesabackend.controller;

import org.example.jesabackend.dto.PunchDTO;
import org.example.jesabackend.dto.PunchStatsDTO;
import org.example.jesabackend.model.Punch;
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

    public PunchController(PunchService punchService) {
        this.punchService = punchService;
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

    @PostMapping
    public Punch createPunch(@RequestBody Punch punch) {
        return punchService.createPunch(punch);
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
}