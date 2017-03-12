package com.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.team4.entity.Employer;


public interface EmployerRepository extends JpaRepository<Employer, Long> {
	Employer findByUsername(@Param("username") String username);
}
