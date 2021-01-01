package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.BookedProductModel;

public interface IBookedProductDAO extends GenericDAO<BookedProductModel> {
	List<BookedProductModel> findByUserIdAndOrder(Integer id, Integer orderId);
	BookedProductModel findOne(Integer id);
	Integer save(BookedProductModel newBookedProduct);
	void update(BookedProductModel modifyBookedProduct);
	void delete(BookedProductModel deleteBookedProduct);
}
