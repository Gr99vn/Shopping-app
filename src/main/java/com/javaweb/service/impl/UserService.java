package com.javaweb.service.impl;

import javax.inject.Inject;

import com.javaweb.dao.IUserDAO;
import com.javaweb.model.UserModel;
import com.javaweb.service.IUserService;

public class UserService implements IUserService {
	@Inject
	public IUserDAO userDAO;
	
	@Override
	public UserModel findById(int id) {
		return userDAO.findById(id);
	}
	
	@Override
	public boolean findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	@Override
	public UserModel findByUsernamePasswordAndStatus(String username, String password, Integer status) {
		return userDAO.findByUsernamePasswordAndStatus(username, password, status);
	}
	
	@Override
	public void save(UserModel newUser) {
		userDAO.save(newUser);
	}
	
	public void update(UserModel modifyUser) {
		userDAO.update(modifyUser);
	}
	
	@Override
	public void delete(UserModel deleteUser) {
		userDAO.delete(deleteUser);
	}
}
