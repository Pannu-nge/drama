package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Dramas;
import com.exercise.drama.drama.repository.DramasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("dramas")
public class DramasController {

    @Autowired
    private DramasRepository dramasRepository;

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
}
