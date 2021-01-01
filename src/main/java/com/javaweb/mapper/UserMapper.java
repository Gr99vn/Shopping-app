package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel user = new UserModel();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("fullname"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getInt("status"));
			user.setRoleId(rs.getInt("role_id"));
			user.setAddress(rs.getString("address"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			return user;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
