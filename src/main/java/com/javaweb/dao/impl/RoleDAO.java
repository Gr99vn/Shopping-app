package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.IRoleDAO;
import com.javaweb.mapper.RoleMapper;
import com.javaweb.model.RoleModel;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO {
	@Override
	public RoleModel findOne(Integer id) {
		String sql = "SELECT * FROM tbl_role WHERE id = ?";
		List<RoleModel> roles = query(sql, new RoleMapper(), id);
		return roles.size() > 0 ? roles.get(0) : null;
	}
}
