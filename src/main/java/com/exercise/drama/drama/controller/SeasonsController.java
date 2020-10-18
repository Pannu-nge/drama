package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Dramas;
import com.exercise.drama.drama.model.Seasons;
import com.exercise.drama.drama.repository.SeasonsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("seasons")
public class SeasonsController {

    @Autowired
    private SeasonsRepository seasonsRepository;

    @GetMapping
    public List<Seasons> getAll(){ return seasonsRepository.findAll(); }
    @GetMapping("{seasonId}")
    public Seasons getBySeasonId(@PathVariable Long seasonId){
        return seasonsRepository.getOne(seasonId);
    }
    @PostMapping
    public Seasons save(@RequestBody Seasons seasons){
        return seasonsRepository.save(seasons);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Seasons update(@RequestBody Seasons seasons){
        Seasons oldSeason = seasonsRepository.getOne(seasons.getSeasonId());
        BeanUtils.copyProperties(seasons, oldSeason, "seasonId");
        return seasonsRepository.saveAndFlush(oldSeason);
    }
    @RequestMapping(value = "{seasonId}", method = RequestMethod.DELETE)
    public void deleteBySeasonId(@PathVariable Long seasonId){
        seasonsRepository.deleteById(seasonId);
    }
}
