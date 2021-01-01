package com.javaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IProductDAO;
import com.javaweb.model.ProductModel;
import com.javaweb.paging.Pageble;
import com.javaweb.service.IProductService;

public class ProductService implements IProductService {
	@Inject
	public IProductDAO productDAO;
	
	@Override
	public Integer getTotalItem() {
		return productDAO.getTotalItem();
	}
	
	@Override
	public List<ProductModel> searchByName(String name) {
		return productDAO.searchByName(name);
	}

	@Override
	public List<ProductModel> searchByName(String name, Pageble pageble) {
		return productDAO.searchByName(name, pageble);
	}
	
	@Override
	public List<ProductModel> findAll(Pageble pageble) {
		return productDAO.findAll(pageble);
	}
	
	@Override
	public ProductModel findById(Integer id) {
		return productDAO.findById(id);
	}
	
	@Override
	public ProductModel save(ProductModel newProduct) {
		return productDAO.save(newProduct);
	}
	
	@Override
	public List<ProductModel> findByName(String name) {
		return productDAO.findByName(name);
	}
	
	@Override
	public ProductModel update(ProductModel product) {
		productDAO.update(product);
		return productDAO.findProductBaseById(product.getId());
	}
	
	@Override
	public void delete(ProductModel product) {
		productDAO.delete(product);
	}
}
