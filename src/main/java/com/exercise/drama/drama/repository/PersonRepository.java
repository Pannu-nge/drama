package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
