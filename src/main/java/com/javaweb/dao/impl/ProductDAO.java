package com.javaweb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IColorSourceDAO;
import com.javaweb.dao.IProductDAO;
import com.javaweb.mapper.ProductMapper;
import com.javaweb.model.ProductModel;
import com.javaweb.paging.Pageble;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {
	@Inject
	public IColorSourceDAO colorSourceDAO;
	
	@Override
	public Integer getTotalItem() {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_product");
		return query(sql.toString(), new ProductMapper()).size();
	}

	@Override
	public List<ProductModel> searchByName(String name) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_product WHERE product_name LIKE '%"+name+"%'");
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public List<ProductModel> searchByName(String name, Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_product WHERE product_name LIKE '%"+name+"%'");
		if (pageble.getSorter().getSortBy() != null && pageble.getSorter().getSortName() != null) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortName() );
		} 
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+ pageble.getOffset() + "," + pageble.getLimit());
		} 
		return query(sql.toString(), new ProductMapper());
	}
	
	@Override
	public List<ProductModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_product");
//		if (pageble.getSorter().getSortBy() != null && pageble.getSorter().getSortName() != null) {
//			sql.append(" ORDER BY " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortName() );
//		} 
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+ pageble.getOffset() + "," + pageble.getLimit());
		} 
		return query(sql.toString(), new ProductMapper());
	}
	
	@Override
	public ProductModel findById(Integer id) {
		ProductModel product = findProductBaseById(id);
		product.setColorSources(colorSourceDAO.findColorSourceByProductId(product.getId()));
		return product;
	}
	
	@Override
	public ProductModel findProductBaseById(Integer id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_product AS p WHERE p.id = ?");;
		List<ProductModel> products = new ArrayList<ProductModel>();
		products = query(sql.toString(), new ProductMapper(), id);
		return products.size() > 0 ? products.get(0) : null;
	}
	
	@Override
	public ProductModel save(ProductModel newProduct) {
		StringBuilder sql = new StringBuilder("INSERT INTO tbl_product(product_name, default_source, price, des)")
				.append(" VALUES(?, ?, ?, ?)");
		int id = insert(sql.toString(), newProduct.getName(), newProduct.getDefaultSource(), newProduct.getPrice(), newProduct.getDes());
		return findProductBaseById(id);
	}
	
	@Override
	public List<ProductModel> findByName(String name) {
		String sql = "SELECT * FROM tbl_product WHERE product_name LIKE ?";
		List<ProductModel> products = query(sql, new ProductMapper(), "%"+name+"%");
		for (ProductModel product : products) {
			product.setColorSources(colorSourceDAO.findColorSourceByProductId(product.getId()));
		}
		return products;
	}
	
	@Override
	public void update(ProductModel product) {
		StringBuilder sql = new StringBuilder("UPDATE tbl_product SET product_name = ?, default_source = ?")
				.append(", price = ?, des = ? WHERE id = ?");
		updateOrDelete(sql.toString(), product.getName(), product.getDefaultSource(), product.getPrice(), product.getDes(),
				product.getId());
	}
	
	@Override
	public void delete(ProductModel product) {
		String sql = "DELETE FROM tbl_product WHERE id = ?";
		updateOrDelete(sql, product.getId());
	}
}
