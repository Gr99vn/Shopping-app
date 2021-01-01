package com.javaweb.service;

import com.javaweb.model.UserModel;

public interface IUserService {
	UserModel findById(int id);
	boolean findByUsername(String username);
	UserModel findByUsernamePasswordAndStatus(String username, String password, Integer status);
	void save(UserModel newUser);
	void update(UserModel modifyUser);
	void delete(UserModel deleteUser);
}
