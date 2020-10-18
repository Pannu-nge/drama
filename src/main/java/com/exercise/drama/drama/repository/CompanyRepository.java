package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
