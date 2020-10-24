package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Dramas;
import com.exercise.drama.drama.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DramasRepository extends JpaRepository<Dramas, Long> {

    public List<Dramas> findByGenres_GenreId(Long genreId);
    public List<Dramas> findByCrews_Person_PersonId(Long personId);

}
