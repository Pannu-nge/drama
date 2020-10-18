package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodesRepository extends JpaRepository<Episodes, Long> {
}
