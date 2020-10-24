package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonsRepository extends JpaRepository<Seasons, Long> {

    public List<Seasons> findByDramaId(Long dramaId);
}
