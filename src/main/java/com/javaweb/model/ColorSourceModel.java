package com.javaweb.model;

public class ColorSourceModel {
	private int id;
	private int colorId;
	private int productId;
	private ColorModel color;
	private String source;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ColorModel getColor() {
		return color;
	}
	public void setColor(ColorModel color) {
		this.color = color;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
}
