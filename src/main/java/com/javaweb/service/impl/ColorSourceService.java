package com.javaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IColorSourceDAO;
import com.javaweb.model.ColorSourceModel;
import com.javaweb.service.IColorSourceService;

public class ColorSourceService implements IColorSourceService {
	@Inject
	public IColorSourceDAO colorSourceDAO;
	
	@Override
	public List<ColorSourceModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ColorSourceModel save(ColorSourceModel colorSourceModel) {
		return colorSourceDAO.save(colorSourceModel);
	}
	
	@Override
	public void delete(ColorSourceModel colorSource) {
		colorSourceDAO.delete(colorSource);
	}
}
