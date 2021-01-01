package com.javaweb.utils;

import com.javaweb.model.BookedProductModel;
import com.javaweb.model.OrderModel;

public class ComputeUtil {
	public static int totalAmount(OrderModel order) {
		int totalAmount = 0;
		for (BookedProductModel bpm : order.getBookedProducts()) {
			totalAmount += bpm.getQuantity() * bpm.getProduct().getPrice();
		}
		return totalAmount;
	}
	
}
