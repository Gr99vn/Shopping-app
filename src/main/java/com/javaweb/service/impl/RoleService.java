package com.javaweb.service.impl;

import javax.inject.Inject;

import com.javaweb.dao.IRoleDAO;
import com.javaweb.model.RoleModel;
import com.javaweb.service.IRoleService;

public class RoleService implements IRoleService {
	@Inject
	public IRoleDAO roleDAO;
	
	@Override
	public RoleModel findOne(Integer id) {
		return roleDAO.findOne(id);
	}
}
