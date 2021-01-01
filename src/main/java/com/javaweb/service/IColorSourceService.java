package com.javaweb.service;

import java.util.List;

import com.javaweb.model.ColorSourceModel;

public interface IColorSourceService {
	List<ColorSourceModel> findAll();
	ColorSourceModel save(ColorSourceModel colorSourceModel);
	void delete(ColorSourceModel colorSource);
}
