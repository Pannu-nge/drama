package com.exercise.drama.drama.repository;

import com.exercise.drama.drama.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
