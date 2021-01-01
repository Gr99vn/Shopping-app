package com.javaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class JspFormUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> classT, HttpServletRequest request) {
		T obj = null;
		try {
			obj = classT.newInstance();
			BeanUtils.populate(obj, request.getParameterMap()); 
		}
		catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
