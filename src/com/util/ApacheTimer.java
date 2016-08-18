package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;


/**
 * 
* <p>Title:ApacheTimer </p>
* <p>Description: 定时任务</p>
* @author tianshiyeben
* @date Aug 18, 2016
 */
public class ApacheTimer extends TimerTask {

	 
	 public ApacheTimer() { 
		 super();
	 } 

	 @Override 
	 public void run() { 
         Date now = new Date();
         //每小时55分钟时候，扫描一次端口
		 int mins = now.getMinutes();
		 InputStream in = null; 
		 String[] cmds = {"/bin/sh","-c","lsof -i :8080"};
		 String dir = StaticKeys.ROOT;
		 Process process;
		 String pid = "";
         if(mins==55){
        	String[] port = StaticKeys.getPorts();
        	for(String p : port){
        		if(!keepTomcatAlive(p)){
                	System.out.println(new Date()+"重新启动tomcat端口---"+p);
                	cmds[2]="lsof -i :"+p;
        			try {
        				 process = Runtime.getRuntime().exec(cmds);
        				 int i = process.waitFor();  
        				 in = process.getInputStream();  
        		         BufferedReader read = new BufferedReader(new InputStreamReader(in));  
        		         String result = read.readLine();  
        		         result = read.readLine();
        		         System.out.println("INFO:"+result);
        		         in.close();
        		         if(!StringUtils.isEmpty(result)){
        		        	 pid = result.substring(4,14).replace(" ", "");
        			         String[] cmdsKill = {"/bin/sh","-c","kill -s 9 "+pid};
        			         process = Runtime.getRuntime().exec(cmdsKill);
        					 process.waitFor();
        		         }
        				 process = RuntimeUtils.exec("sh restart_"+p+".sh",null,dir);
        				 process.waitFor();
        				 MailUtils.sendMail(StaticKeys.getHost()+",端口："+p+"的tomcat未响应通知", "端口："+p+"，进程ID："+pid+"的tomcat已经宕机，正在重启服务");
        			} catch (Exception e) {
        				 System.out.println("重启tomcat端口"+p+"异常："+e.toString());
        			} 
        		}
        	}
        	
         }
	 } 
	 
	 /**
	  * 检测该端口的tomcat是否有响应
	  * @param port
	  * @return
	  * @throws NullPointerException
	  */
	 private static boolean keepTomcatAlive(String port) throws NullPointerException {
		 String s;
		 boolean isTomcatAlive = false;
		 java.io.BufferedReader in;
		 System.setProperty("sun.net.client.defaultConnectTimeout", "8000");   
		 System.setProperty("sun.net.client.defaultReadTimeout", "10000");  
		 try {
		     URL url = new URL("http://localhost:"+port);
		     URLConnection con = url.openConnection();
		     in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
		     con.setConnectTimeout(2000);
		     con.setReadTimeout(5000);
		     while ((s = in.readLine()) != null) {
		     if (s.length() > 0) {
		         //accessed page successful.
		         return true;
		         }
		     }
		     in.close();
		 } catch (Exception ex) {
		           System.out.println("端口读取异常："+ex.toString());
		 }
		 return isTomcatAlive;
		 }
	 

}
