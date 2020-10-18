package com.exercise.drama.drama.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.SecureRandom;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    private String genreType;

    public Long getGenreId() {
        return genreId;
    }
    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
    public String getGenreType() {
        return genreType;
    }
    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }
}


