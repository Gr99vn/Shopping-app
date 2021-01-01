package com.javaweb.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IBookedProductDAO;
import com.javaweb.dao.IColorSourceDAO;
import com.javaweb.dao.IProductDAO;
import com.javaweb.mapper.BookedProductMapper;
import com.javaweb.model.BookedProductModel;

public class BookedProductDAO extends AbstractDAO<BookedProductModel> implements IBookedProductDAO {
	@Inject 
	public IColorSourceDAO colorSourceDAO;
	
	@Inject
	public IProductDAO productDAO;

	@Override
	public List<BookedProductModel> findByUserIdAndOrder(Integer id, Integer orderId) {
		List<BookedProductModel> results;
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_booked_product WHERE user_id = ?");
		if (orderId == null) {
			sql.append(" AND order_id IS NULL");
			results = query(sql.toString(), new BookedProductMapper(), id);
		} else {
			sql.append(" AND order_id = ?");
			results = query(sql.toString(), new BookedProductMapper(), id, orderId);
		}
		for(BookedProductModel bookedProduct : results) {
			bookedProduct.setProduct(productDAO.findProductBaseById(bookedProduct.getProductId()));
			bookedProduct.setColorSource(colorSourceDAO.findbyId(bookedProduct.getColorSourceId()));
		}
		return results;
	}

	@Override
	public BookedProductModel findOne(Integer id) {
		String sql = "SELECT * FROM tbl_booked_product WHERE id = ?";
		List<BookedProductModel> results = query(sql, new BookedProductMapper(), id);
		return results.size() > 0 ? results.get(0) : null;
	}
	
	@Override
	public Integer save(BookedProductModel newBookedProduct) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder("INSERT INTO tbl_booked_product(user_id, product_id, color_source_id")
				.append(", saleoff, time, quantity) VALUES(?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newBookedProduct.getUserId(), newBookedProduct.getProductId()
				, newBookedProduct.getColorSourceId(), newBookedProduct.getSaleOff()
				, time, newBookedProduct.getQuantity());
	}
	
	@Override
	public void update(BookedProductModel modifyBookedProduct) {
		StringBuilder sql = new StringBuilder("UPDATE tbl_booked_product");
		if (modifyBookedProduct.getOrderId() != -1) {
			sql.append(" SET order_id = ? WHERE id = ?");
			updateOrDelete(sql.toString(), modifyBookedProduct.getOrderId(), modifyBookedProduct.getId());
		} else {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			sql.append(" SET time = ?, quantity = ? WHERE id = ?");
			updateOrDelete(sql.toString(), time, modifyBookedProduct.getQuantity(), modifyBookedProduct.getId());
		}
	}
	
	@Override
	public void delete(BookedProductModel deleteBookedProduct) {
		String sql = "DELETE FROM tbl_booked_product WHERE id = ?";
		updateOrDelete(sql, deleteBookedProduct.getId());
	}
}
