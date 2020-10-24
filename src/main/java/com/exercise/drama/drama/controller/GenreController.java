package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Dramas;
import com.exercise.drama.drama.model.Genre;
import com.exercise.drama.drama.repository.DramasRepository;
import com.exercise.drama.drama.repository.GenreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private DramasRepository dramasRepository;

    @GetMapping
    public List<Genre> getAll(){ return genreRepository.findAll(); }
    @GetMapping("{genreId}")
    public Genre getByGenreId(@PathVariable Long genreId){
        return genreRepository.getOne(genreId);
    }
    @PostMapping
    public Genre save(@RequestBody Genre genre){
        return genreRepository.save(genre);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Genre update(@RequestBody Genre genre){
        Genre oldGenre = genreRepository.getOne(genre.getGenreId());
        BeanUtils.copyProperties(genre, oldGenre, "genreId");
        return genreRepository.saveAndFlush(oldGenre);
    }
    @RequestMapping(value = "{genreId}", method = RequestMethod.DELETE)
    public void deleteByGenreId(@PathVariable Long genreId){
        genreRepository.deleteById(genreId);
    }

    //Searching drama list with genre id
    @GetMapping("dramas/{genreId}")
    public List<Dramas> getDramasByGenreId(@PathVariable Long genreId){
        return dramasRepository.findByGenres_GenreId(genreId);
    }
}
