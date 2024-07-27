package com.scm.service;

import java.util.*;

import com.scm.model.User;

public interface UserService {
	
	User saveUser(User user);
	Optional<User> getUserById(String id);
	Optional<User> updateUser(User user);
	void deleteUser(String id);
	boolean isUserExist(String userId);
	boolean isUserExistByEmail(String Email);
	List<User> getAllUser();
	User getUserByEmail(String email);
	
	
	

}
