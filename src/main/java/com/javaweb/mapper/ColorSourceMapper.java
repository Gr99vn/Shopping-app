package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.ColorSourceModel;

public class ColorSourceMapper implements RowMapper<ColorSourceModel> {
	@Override
	public ColorSourceModel mapRow(ResultSet rs) {
		try {
			ColorSourceModel colorSource = new ColorSourceModel();
			colorSource.setId(rs.getInt("id"));
			colorSource.setProductId(rs.getInt("product_id"));
			colorSource.setColorId(rs.getInt("color_id"));
			colorSource.setSource(rs.getString("source"));
			return colorSource;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
