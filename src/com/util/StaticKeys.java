package com.util;

import java.io.FileNotFoundException;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title:StaticKeys </p>
 * <p>Description: 全局静态变量</p>
 * @author tianshiyeben
 * @date Jun 22, 2016
 */
public class StaticKeys {

	public static String ROOT = System.getProperty("user.dir").replace("bin", "")+"webapps/iptr/WEB-INF/classes/resources";
	
	//需要监听的tomcat端口，多个端口用英文逗号隔开
	private static String[] PORT = null;
	
	//服务器名称
	private static String HOST = "";
	
	/**
	 * 获取监控tomat端口
	 * @return
	 */
	public static String[] getPorts(){
		if(PORT==null){
			String ports = "";
			try {
				ports = PropertyUtil.get(ROOT+"/application.properties", "tomcat.port");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if(!StringUtils.isEmpty(ports)){
				PORT  = ports.split(",");
			}
		}
		return PORT;
	}
	
	/**
	 * 获取服务器名称
	 * @return
	 */
	public static String getHost(){
		if(StringUtils.isEmpty(HOST)){
			 try {
				HOST = PropertyUtil.get(ROOT+"/application.properties", "host");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return HOST;
	}
}
