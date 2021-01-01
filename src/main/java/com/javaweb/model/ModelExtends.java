package com.javaweb.model;

import java.util.ArrayList;
import java.util.List;

public class ModelExtends<T> { 
	private int totalPage;
	private int totalItem;
	private int currentPage;
	private int pageMaxItem;
	private String sortName;
	private String sortBy;
	private List<T> resultList = new ArrayList<>();
	
	public int getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageMaxItem() {
		return pageMaxItem;
	}
	public void setPageMaxItem(int pageMaxItem) {
		this.pageMaxItem = pageMaxItem;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
