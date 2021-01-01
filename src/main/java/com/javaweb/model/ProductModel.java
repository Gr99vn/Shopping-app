package com.javaweb.model;

import java.util.List;

public class ProductModel extends ModelExtends<ProductModel> {
	private int id;
	private String name;
	private String defaultSource;
	private int[] colorSourceIds;
	private List<ColorSourceModel> colorSources;
	private float price;
	private String des;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefaultSource() {
		return defaultSource;
	}
	public void setDefaultSource(String defaultSource) {
		this.defaultSource = defaultSource;
	}
	public List<ColorSourceModel> getColorSources() {
		return colorSources;
	}
	public void setColorSources(List<ColorSourceModel> colorSources) {
		this.colorSources = colorSources;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int[] getColorSourceIds() {
		return colorSourceIds;
	}
	public void setColorSourceIds(int[] colorSourceIds) {
		this.colorSourceIds = colorSourceIds;
	}
}
