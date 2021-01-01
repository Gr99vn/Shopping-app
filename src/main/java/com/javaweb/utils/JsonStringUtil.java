package com.javaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringUtil {
	private String jsonString;

	public JsonStringUtil(String jsonString) {
		this.jsonString = jsonString;
	}
	
	public <T> T toModel(Class<T> classOfT) {
		try {
			return new ObjectMapper().readValue(jsonString, classOfT);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static JsonStringUtil of(BufferedReader br) {
		StringBuilder jsonStringSb = new StringBuilder();
		try {
			String line;
			while ((line = br.readLine()) != null) {
				jsonStringSb.append(line);
			}
		}
		catch (IOException e)  {
			System.out.println(e.getMessage());
		}
		return new JsonStringUtil(jsonStringSb.toString());
	}
}
