package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
