package com.javaweb.dao.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IColorDAO;
import com.javaweb.dao.IColorSourceDAO;
import com.javaweb.mapper.ColorSourceMapper;
import com.javaweb.model.ColorSourceModel;

public class ColorSourceDAO extends AbstractDAO<ColorSourceModel> implements IColorSourceDAO {
	@Inject
	public IColorDAO colorDAO;
	
	@Override
	public List<ColorSourceModel> findColorSourceByProductId(Integer id) {
		StringBuilder sql = new StringBuilder("SELECT *")
				.append(" FROM tbl_color_source AS cs WHERE cs.product_id= ?");
		List<ColorSourceModel> colorSources = query(sql.toString(), new ColorSourceMapper(), id);
		for (ColorSourceModel colorSource : colorSources) {
			colorSource.setColor(colorDAO.findOne(colorSource.getColorId()));
		}
		return colorSources;
	}
	
	@Override
	public ColorSourceModel findbyId(Integer id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_color_source AS cs WHERE cs.id = ?");
		List<ColorSourceModel> colorSources = query(sql.toString(), new ColorSourceMapper(), id);
		if (colorSources.size() > 0) {
			ColorSourceModel colorSource =  colorSources.get(0);
			colorSource.setColor(colorDAO.findOne(colorSource.getColorId()));
			return colorSource;
		}
		return null;
	}
	
	@Override
	public ColorSourceModel save(ColorSourceModel colorSourceModel) {
		String sql = "INSERT INTO tbl_color_source(product_id, color_id, source) VALUES(?, ?, ?)";
		int colorSourceId = insert(sql, colorSourceModel.getProductId(), colorSourceModel.getColorId(), colorSourceModel.getSource());
		return findbyId(colorSourceId);
	}
	
	@Override
	public void delete(ColorSourceModel colorSource) {
		String sql = "DELETE FROM tbl_color_source WHERE id = ?";
		updateOrDelete(sql, colorSource.getId());
	}
}
