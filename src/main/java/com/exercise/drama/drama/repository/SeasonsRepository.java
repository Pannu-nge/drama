package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonsRepository extends JpaRepository<Seasons, Long> {
}
