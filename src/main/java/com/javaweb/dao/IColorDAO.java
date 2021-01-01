package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.ColorModel;

public interface IColorDAO extends GenericDAO<ColorModel>{
	ColorModel findOne(Integer id);
	List<ColorModel> findAll();
	void save(ColorModel colorModel);
}
