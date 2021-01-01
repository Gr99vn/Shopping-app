package com.javaweb.service;

import java.util.List;

import com.javaweb.model.ColorModel;

public interface IColorService {
	ColorModel findOne(Integer id);
	List<ColorModel> findAll();
	void save(ColorModel colorModel);
}
