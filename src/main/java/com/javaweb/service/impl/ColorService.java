package com.javaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IColorDAO;
import com.javaweb.model.ColorModel;
import com.javaweb.service.IColorService;

public class ColorService implements IColorService {
	@Inject
	public IColorDAO colorDAO;
	
	@Override
	public ColorModel findOne(Integer id) {
		return colorDAO.findOne(id);
	}
	
	@Override
	public List<ColorModel> findAll() {
		return colorDAO.findAll();
	}
	
	@Override
	public void save(ColorModel colorModel) {
		colorDAO.save(colorModel);		
	}
}
