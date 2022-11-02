package com.banking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.user.entity.User;
@Repository
public interface UserRepository extends  JpaRepository<User,Integer>{
public User findByUsername(String name);
public User findByEmail(String name);
}
