package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.ColorModel;

public class ColorMapper implements RowMapper<ColorModel> {
	@Override
	public ColorModel mapRow(ResultSet rs) {
		try {
			ColorModel color = new ColorModel();
			color.setId(rs.getInt("id"));
			color.setName(rs.getString("color_name"));
			color.setCode(rs.getString("color_code"));
			return color;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
