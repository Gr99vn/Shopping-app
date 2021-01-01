package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.ColorSourceModel;

public interface IColorSourceDAO extends GenericDAO<ColorSourceModel>{
	List<ColorSourceModel> findColorSourceByProductId(Integer id);
	ColorSourceModel findbyId(Integer id);
	ColorSourceModel save(ColorSourceModel colorSourceModel);
	void delete(ColorSourceModel colorSource);
}
