package com.javaweb.paging;

import com.javaweb.sort.Sorter;

public class PageRequest implements Pageble {
	private Integer pageMaxItem;
	private Integer currentPage;
	private Sorter sorter;
	
	public PageRequest(Integer pageMaxItem, Integer currentPage, Sorter sorter) {
		this.pageMaxItem = pageMaxItem;
		this.currentPage = currentPage;
		this.sorter = sorter;
	}

	@Override
	public Integer getOffset() {
		if (this.currentPage != null && this.pageMaxItem != null)
			return (this.currentPage - 1) * this.pageMaxItem;
		else return null;
	}

	@Override
	public Integer getLimit() {
		return this.pageMaxItem;
	}

	@Override
	public Integer getPage() {
		return this.currentPage;
	}

	@Override
	public Sorter getSorter() {
		return this.sorter;
	}
	
}
