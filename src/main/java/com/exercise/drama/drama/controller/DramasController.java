package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.*;
import com.exercise.drama.drama.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("dramas")
public class DramasController {

    @Autowired
    private DramasRepository dramasRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private SeasonsRepository seasonsRepository;

    @GetMapping
    public List<Dramas> getAll(){
        return dramasRepository.findAll();
    }

    @GetMapping("{dramaId}")
    public Dramas getByDramaId(@PathVariable Long dramaId){
        return dramasRepository.getOne(dramaId);
    }
    @PostMapping
    public Dramas save(@RequestBody Dramas dramas){
        return dramasRepository.save(dramas);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Dramas update(@RequestBody Dramas dramas){
        Dramas oldDrama = dramasRepository.getOne(dramas.getDramaId());
        BeanUtils.copyProperties(dramas, oldDrama, "dramaId", "releasedDate");
        return dramasRepository.saveAndFlush(oldDrama);
    }
    @RequestMapping(value = "{dramaId}", method = RequestMethod.DELETE)
    public void deleteByDramaId(@PathVariable Long dramaId){
        dramasRepository.deleteById(dramaId);
    }

    //Adding genre into drama
    @PostMapping(value = "genre")
    public Dramas addGenre(@RequestBody Map<String, Long> dramagenre) {
        Dramas dramas=dramasRepository.getOne(dramagenre.get("dramaId"));
        Set<Genre> genres = dramas.getGenres();
        if (genres == null) {
            genres = new HashSet<>();
        }
        genres.add(genreRepository.getOne(dramagenre.get("genreId")));
        dramas.setGenres(genres);
        return dramasRepository.saveAndFlush(dramas);
    }

    //Removing genre from drama
    @DeleteMapping(value = "genre")
    public Dramas removeGenre(@RequestBody Map<String, Long> dramagenre) {
        Dramas dramas=dramasRepository.getOne(dramagenre.get("dramaId"));
        Set<Genre> genres = dramas.getGenres();
        if (genres == null) {
            genres = new HashSet<>();
        }
        genres.remove(genreRepository.getOne(dramagenre.get("genreId")));
        dramas.setGenres(genres);
        return dramasRepository.saveAndFlush(dramas);
    }

    //Finding seasons with drama id
    @GetMapping("seasons/{dramaId}")
    public List<Seasons> getSeasonsByDramaId(@PathVariable Long dramaId){
        return seasonsRepository.findByDramaId(dramaId);
    }

}
