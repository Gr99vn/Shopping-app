package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{
	@Override
	public RoleModel mapRow(ResultSet rs) {
		try {
			RoleModel role = new RoleModel();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("role_name"));
			role.setCode(rs.getString("role_code"));
			role.setDes(rs.getString("des"));
			return role;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
