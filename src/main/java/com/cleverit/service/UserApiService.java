package com.cleverit.service;

import java.util.List;

import com.cleverit.entity.User;

public interface UserApiService {

	List<User> list();

	boolean createUser(User user);
	
	boolean deleteUser(String id);
	
	boolean editUser(User user);
	
	User findUser(String id);
		
}
