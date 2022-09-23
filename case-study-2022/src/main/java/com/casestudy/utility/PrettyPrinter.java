package com.casestudy.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class PrettyPrinter {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create() ; 
	public static String toJson(Object obj) {
		return gson.toJson(obj); 
	}
}
