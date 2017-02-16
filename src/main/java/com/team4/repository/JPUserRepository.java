package com.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.team4.entity.JPUser;


public interface JPUserRepository extends JpaRepository<JPUser, Long> {

	JPUser findByUsernameIgnoreCase(@Param("username") String username);

}