package com.util;

import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * <p>Title:TomServlet </p>
 * <p>Description:定时任务，每分钟执行一次 </p>
 * @author tianshiyeben
 * @date Feb 1, 2016
 */
public class TomServlet  extends HttpServlet {

	@Override  
    public void init() throws ServletException {  
		 super.init();  
		 Timer timer = new Timer(); 
		 long delay2 = 60 * 1000; 
		 long period2 = 60 * 1000; 
		 // 从现在开始一分钟之后，每隔 一分钟执行一次  
		 timer.schedule(new ApacheTimer(), delay2, period2);  
    }  
}
