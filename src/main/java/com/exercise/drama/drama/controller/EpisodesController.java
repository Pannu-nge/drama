package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Dramas;
import com.exercise.drama.drama.model.Episodes;
import com.exercise.drama.drama.repository.EpisodesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("episodes")
public class EpisodesController {

    @Autowired
    private EpisodesRepository episodesRepository;

    @GetMapping
    public List<Episodes> getAll(){ return episodesRepository.findAll(); }
    @GetMapping("{episodeId}")
    public Episodes getByEpisodeId(@PathVariable Long episodeId){
        return episodesRepository.getOne(episodeId);
    }
    @PostMapping
    public Episodes save(@RequestBody Episodes episodes){
        return episodesRepository.save(episodes);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Episodes update(@RequestBody Episodes episodes){
        Episodes oldEpisode = episodesRepository.getOne(episodes.getEpisodeId());
        BeanUtils.copyProperties(episodes, oldEpisode, "episodeId", "seasonId");
        return episodesRepository.saveAndFlush(oldEpisode);
    }
    @RequestMapping(value = "{episodeId}", method = RequestMethod.DELETE)
    public void deleteByEpisodeId(@PathVariable Long episodeId){
        episodesRepository.deleteById(episodeId);
    }
}
