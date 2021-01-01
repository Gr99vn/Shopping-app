package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{
	@Override
	public ProductModel mapRow(ResultSet rs) {
		try {
			ProductModel product = new ProductModel();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("product_name"));
			product.setDefaultSource(rs.getString("default_source"));
			product.setPrice(rs.getFloat("price"));
			product.setDes(rs.getString("des"));
			return product;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
