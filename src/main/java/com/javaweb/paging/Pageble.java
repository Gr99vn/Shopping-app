package com.javaweb.paging;

import com.javaweb.sort.Sorter;

public interface Pageble {
	Integer getOffset();
	Integer getLimit();
	Integer getPage();
	Sorter getSorter();
}
