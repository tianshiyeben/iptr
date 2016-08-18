package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
* <p>Title:PropertyUtil </p>
* <p>Description:获取properties的工具类 </p>
* @author tianshiyeben
* @date Aug 18, 2016
 */
public class PropertyUtil {
	
	public static String get(String fileName,String propertyName) throws FileNotFoundException {
		InputStream in = new FileInputStream(fileName);  
		Properties p = new Properties();   
		String msg = "";
		try {
			p.load(in);
			msg = (String)p.get(propertyName);
		} catch (IOException e) {
			e.printStackTrace();
		}  

		return msg;
	}
	
}
