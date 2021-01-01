package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.IColorDAO;
import com.javaweb.mapper.ColorMapper;
import com.javaweb.model.ColorModel;

public class ColorDAO extends AbstractDAO<ColorModel> implements IColorDAO {
	@Override
	public ColorModel findOne(Integer id) {
		String sql = "SELECT * FROM tbl_color WHERE id = ?";
		List<ColorModel> colors = query(sql, new ColorMapper(), id);
		return colors.size() > 0 ? colors.get(0) : null;
	}
	
	@Override
	public List<ColorModel> findAll() {
		String sql = "SELECT * FROM tbl_color";
		List<ColorModel> colors = query(sql, new ColorMapper());
		return colors;
	}
	
	@Override
	public void save(ColorModel colorModel) {
		String sql = "INSERT INTO tbl_color(name, code) VALUES(?, ?)";
		insert(sql, colorModel.getName(), colorModel.getCode());
	}
}
