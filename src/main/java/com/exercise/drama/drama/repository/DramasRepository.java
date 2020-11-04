package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Dramas;
import com.exercise.drama.drama.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DramasRepository extends JpaRepository<Dramas, Long> {

    public List<Dramas> findByGenres_GenreId(Long genreId);
    public List<Dramas> findByCrews_Person_PersonId(Long personId);

    @Modifying
    @Query(value = "insert into drama_crew values (:dramaId, :roleId, :personId)", nativeQuery = true)
    public void saveDramaCrew(@Param("dramaId") Long dramaId, @Param("roleId") Long roleId, @Param("personId") Long personId);
}
