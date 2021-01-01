package com.javaweb.service;

import java.util.List;

import com.javaweb.model.ProductModel;
import com.javaweb.paging.Pageble;

public interface IProductService {
	Integer getTotalItem();
	List<ProductModel> searchByName(String name);
	List<ProductModel> searchByName(String name, Pageble pageble);
	List<ProductModel> findAll(Pageble pageble);
	ProductModel findById(Integer id);
	ProductModel save(ProductModel newProduct);
	List<ProductModel> findByName(String name);
	ProductModel update(ProductModel product);
	void delete(ProductModel product);
}
