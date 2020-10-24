package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodesRepository extends JpaRepository<Episodes, Long> {

    public List<Episodes> findBySeasonId(Long seasonId);
}
