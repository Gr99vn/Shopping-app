package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BookedProductModel;

public interface IBookedProductService {
	List<BookedProductModel> findByUserIdAndOrder(Integer id, Integer orderId);
	BookedProductModel findOne(Integer id);
	BookedProductModel save(BookedProductModel newBookedProduct);
	BookedProductModel update(BookedProductModel modifyBookedProduct);
	void delete(BookedProductModel deleteBookedProduct);
}
