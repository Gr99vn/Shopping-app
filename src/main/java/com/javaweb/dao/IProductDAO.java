package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.ProductModel;
import com.javaweb.paging.Pageble;

public interface IProductDAO extends GenericDAO<ProductModel> {
	Integer getTotalItem();
	List<ProductModel> searchByName(String name);
	List<ProductModel> searchByName(String name, Pageble pageble);
	List<ProductModel> findAll(Pageble pageble);
	ProductModel findProductBaseById(Integer id);
	ProductModel findById(Integer id);
	ProductModel save(ProductModel newProduct);
	List<ProductModel> findByName(String name);
	void update(ProductModel product);
	void delete(ProductModel product);
}
