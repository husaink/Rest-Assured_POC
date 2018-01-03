package utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
 

public class Helpers {

	
	public static String readFile(String json) throws IOException
	{
		ClassLoader classLoader = Helpers.class.getClassLoader();
		InputStream in = classLoader.getResourceAsStream(json);
		String content = IOUtils.toString(in);
		return content;
	}
	
}
