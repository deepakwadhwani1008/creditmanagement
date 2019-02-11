package com.tsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsf.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
