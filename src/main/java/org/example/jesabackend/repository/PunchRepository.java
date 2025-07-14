package org.example.jesabackend.repository;

import org.example.jesabackend.model.Punch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunchRepository extends JpaRepository<Punch, Long> {
}