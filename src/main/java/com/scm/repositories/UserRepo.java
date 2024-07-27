package com.scm.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	
	//extra methods db related operation
	
	//custom query methods
	
	//custom finder methods
	
	Optional<User>findByEmail(String email);

}
