package com.digital.headers;

import java.util.HashMap;
import java.util.Map;

public class Header {

	
	public static Map<String,String> setHeaders()
	{
		Map<String,String> header = new HashMap<String, String>();
		header.put("Content-type", "application/json;");
		return header;
		
	}
	
}
