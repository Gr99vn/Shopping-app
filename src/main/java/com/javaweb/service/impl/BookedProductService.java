package com.javaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IBookedProductDAO;
import com.javaweb.model.BookedProductModel;
import com.javaweb.service.IBookedProductService;

public class BookedProductService implements IBookedProductService {
	@Inject
	public IBookedProductDAO bookedProductDAO;
	
	@Override
	public List<BookedProductModel> findByUserIdAndOrder(Integer id, Integer orderId) {
		return bookedProductDAO.findByUserIdAndOrder(id, orderId);
	}
	
	@Override
	public BookedProductModel findOne(Integer id) {
		return bookedProductDAO.findOne(id);
	}
	
	@Override
	public BookedProductModel save(BookedProductModel newBookedProduct) {
		return bookedProductDAO.findOne(bookedProductDAO.save(newBookedProduct));
	}
	
	@Override
	public BookedProductModel update(BookedProductModel modifyBookedProduct) {
		bookedProductDAO.update(modifyBookedProduct);
		return bookedProductDAO.findOne(modifyBookedProduct.getId());
	}
	
	@Override
	public void delete(BookedProductModel deleteBookedProduct) {
		bookedProductDAO.delete(deleteBookedProduct);
	}
}
