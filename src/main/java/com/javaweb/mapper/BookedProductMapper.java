package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.BookedProductModel;

public class BookedProductMapper implements RowMapper<BookedProductModel> {
	@Override
	public BookedProductModel mapRow(ResultSet rs) {
		try {
			BookedProductModel bookedProduct = new BookedProductModel();
			bookedProduct.setId(rs.getInt("id"));
			bookedProduct.setUserId(rs.getInt("user_id"));
			bookedProduct.setProductId(rs.getInt("product_id"));
			bookedProduct.setColorSourceId(rs.getInt("color_source_id"));
			bookedProduct.setSaleOff(rs.getFloat("saleoff"));
			bookedProduct.setTime(rs.getTimestamp("time"));
			bookedProduct.setQuantity(rs.getInt("quantity"));
			bookedProduct.setOrderId(rs.getInt("order_id"));
			return bookedProduct;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
