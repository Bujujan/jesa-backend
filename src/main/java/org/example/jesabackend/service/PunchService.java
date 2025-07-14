package org.example.jesabackend.service;


import org.example.jesabackend.dto.PunchDTO;
import org.example.jesabackend.dto.PunchStatsDTO;
import org.example.jesabackend.model.Punch;
import org.example.jesabackend.repository.PunchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PunchService {

    private final PunchRepository punchRepository;

    public PunchService(org.example.jesabackend.repository.PunchRepository punchRepository) {
        this.punchRepository = punchRepository;
    }

    public List<org.example.jesabackend.model.Punch> getAllPunches() {
        return punchRepository.findAll();
    }

    public Optional<Punch> getPunchById(Long id) {
        return punchRepository.findById(id);
    }

    public Punch createPunch(Punch punch) {
        return punchRepository.save(punch);
    }

    public Optional<Punch> updatePunch(Long id, Punch punch) {
        return punchRepository.findById(id).map(existing -> {
            existing.setTitle(punch.getTitle());
            existing.setDescription(punch.getDescription());
            existing.setStatus(punch.getStatus());
            existing.setModifiedBy(punch.getModifiedBy());
            existing.setProject(punch.getProject());
            return punchRepository.save(existing);
        });
    }

    public boolean deletePunch(Long id) {
        return punchRepository.findById(id).map(punch -> {
            punchRepository.delete(punch);
            return true;
        }).orElse(false);
    }

    public List<PunchDTO> getAllPunchesDTO() {
        return punchRepository.findAll().stream().map(punch -> {
            return new PunchDTO(
                    punch.getId(),
                    punch.getTitle(),
                    punch.getDescription(),
                    punch.getStatus().name(),
                    punch.getProject() != null ? punch.getProject().getId() : null,
                    punch.getCreatedBy() != null ? punch.getCreatedBy().getName() : null,
                    punch.getModifiedBy() != null ? punch.getModifiedBy().getName() : null,
                    punch.getCreatedAt(),
                    punch.getUpdatedAt()
            );
        }).collect(Collectors.toList());
    }

    public PunchStatsDTO getPunchStats() {
        List<Punch> allPunches = punchRepository.findAll();

        long total = allPunches.size();

        long newThisMonth = allPunches.stream()
                .filter(p -> p.getCreatedAt() != null &&
                        p.getCreatedAt().getMonth().equals(LocalDate.now().getMonth()))
                .count();

        long resolved = allPunches.stream()
                .filter(p -> p.getStatus() == Punch.Status.RESOLVED)
                .count();

        long pending = allPunches.stream()
                .filter(p -> p.getStatus() == Punch.Status.OPEN || p.getStatus() == Punch.Status.IN_PROGRESS)
                .count();

        return new PunchStatsDTO(total, newThisMonth, resolved, pending);
    }
}