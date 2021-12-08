package com.example.SpringBootDemoTask.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootDemoTask.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	User findByUserName(String username);
	
}
