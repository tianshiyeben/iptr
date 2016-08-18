package com.util;

import java.io.FileNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * <p>Title:MailUtils </p>
 * <p>Description:利用 Apache Commons Email 组件（commons-email-1.1）发送邮件  </p>
 * @author tianshiyeben
 * @date Jun 22, 2016
 */
public class MailUtils {

	//目标邮件地址
	private static String toMail = "";
	//发送邮件地址
	private static String send_mail = "";
	//发送邮件密码
	private static String send_pwd = "";
	//发送邮件服务器
	private static String mail_host = "";
	
	/**
	 * 
	 * @param to 目标邮件地址
	 * @param title 标题
	 * @param content 内容
	 */
	public static void sendMail(String title,String content){
	  if(StringUtils.isEmpty(toMail)){
		  try {
			  toMail = PropertyUtil.get(StaticKeys.ROOT+"/application.properties", "to_mail");
			  send_mail = PropertyUtil.get(StaticKeys.ROOT+"/application.properties", "send_mail");
			  send_pwd = PropertyUtil.get(StaticKeys.ROOT+"/application.properties", "send_pwd");
			  mail_host = PropertyUtil.get(StaticKeys.ROOT+"/application.properties", "mail_host");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  }
	  if(!"0".equals(toMail)){
	      HtmlEmail email = new HtmlEmail();//可以发送html类型的邮件  
	      email.setHostName(mail_host);//指定要使用的邮件服务器  
	      email.setAuthentication(send_mail, send_pwd);//使用163的邮件服务器需提供在163已注册的用户名、密码  
	      email.setCharset("UTF-8");  
	      try {  
	          email.setFrom(send_mail);//设置发件人  
	          email.addTo(toMail.split(","));//设置收件人  
	          email.setSubject(title);//设置主题  
	          email.setMsg(content);//设置邮件内容  
	          email.send();  
	      } catch (EmailException e) {  
	          e.printStackTrace();  
	      }  
	  }
	}
	
}
