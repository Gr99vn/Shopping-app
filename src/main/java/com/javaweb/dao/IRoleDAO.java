package com.javaweb.dao;

import com.javaweb.model.RoleModel;

public interface IRoleDAO extends GenericDAO<RoleModel>{
	RoleModel findOne(Integer id);
}
