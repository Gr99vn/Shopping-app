package com.javaweb.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;

import com.javaweb.dao.IRoleDAO;
import com.javaweb.dao.IUserDAO;
import com.javaweb.mapper.UserMapper;
import com.javaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{
	@Inject
	public IRoleDAO roleDAO;
	
	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM tbl_user WHERE id = ?";
		List<UserModel> users = query(sql, new UserMapper(), id);
		return users.size() > 0 ? users.get(0) : null;
	}
	
	@Override
	public boolean findByUsername(String username) {
		String sql = "SELECT * FROM tbl_user WHERE username = ?";
		List<UserModel> users = query(sql, new UserMapper(), username);
		return users.size() > 0 ? true : false;
	}
	
	@Override
	public UserModel findByUsernamePasswordAndStatus(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_user AS u")
				.append(" WHERE u.username = ? AND u.status = ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), username, status);
		if (users.size() > 0){
			UserModel user = users.get(0);
			String hashed = user.getPassword();
			try {
				if (BCrypt.checkpw(password, hashed)) {
					user.setRole(roleDAO.findOne(user.getRoleId()));
					return user;
				} else {
					return null;
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid salt ver");
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void save(UserModel newUser) {
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt(12));
		StringBuilder sql = new StringBuilder("INSERT INTO tbl_user(fullname, username, password, status, role_id, address, email, phone)")
				.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		insert(sql.toString(), newUser.getName(), newUser.getUsername(), hashed, newUser.getStatus(), newUser.getRoleId(), newUser.getAddress(), newUser.getEmail(), newUser.getPhone());
	}
	
	@Override
	public void update(UserModel modifyUser) {
		String hashed = BCrypt.hashpw(modifyUser.getPassword(), BCrypt.gensalt(12));
		StringBuilder sql = new StringBuilder("UPDATE tbl_user SET fullname = ?, password = ?, status = ?, role_id = ?, address = ?")
				.append(" email = ?, phone = ? WHERE id = ?");
		updateOrDelete(sql.toString(), modifyUser.getName(), hashed, modifyUser.getStatus(), modifyUser.getRoleId(), modifyUser.getAddress(), 
				modifyUser.getEmail(), modifyUser.getPhone(), modifyUser.getId());	
	}
	
	@Override
	public void delete(UserModel deleteUser) {
	//	String sql = "DELETE FROM tbl_user WHERE id = ?";
		String sql = "UPDATE tbl_user SET status = 0 WHERE id = ?";
		updateOrDelete(sql, deleteUser.getId());
	}
}
