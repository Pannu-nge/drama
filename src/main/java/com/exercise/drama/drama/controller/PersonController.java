package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Person;
import com.exercise.drama.drama.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getAll(){ return personRepository.findAll(); }
    @GetMapping("{personId}")
    public Person getByPersonId(@PathVariable Long personId){
        return personRepository.getOne(personId);
    }
    @PostMapping
    public Person save(@RequestBody Person person){
        return personRepository.save(person);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Person update(@RequestBody Person person){
        Person oldPerson = personRepository.getOne(person.getPersonId());
        BeanUtils.copyProperties(person, oldPerson, "personId");
        return personRepository.saveAndFlush(oldPerson);
    }
    @RequestMapping(value = "{personId}", method = RequestMethod.DELETE)
    public void deleteByPersonId(@PathVariable Long personId){
        personRepository.deleteById(personId);
    }
}
