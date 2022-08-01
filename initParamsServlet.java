package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@WebServlet("/initParams")
public class initParamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dirPath;
	private String userid;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init() invoked.");
		
		String dirPath = config.getInitParameter("dirPath");
		String userid = config.getInitParameter("userid");
		this.dirPath = dirPath;
		this.userid = userid;
		
		log.info("1. dirPath:{}, userid: {}", dirPath, userid);
		log.info("2. this.dirPath : {}", this.dirPath);
		log.info("3. this.userid : {}", this.userid);

	}//init
  
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service( req , res ) invoked.");
		
		//getInitParameter 호출할 수 없다. init method에서 해야함
//		String dirPath = getInitParameter("dirPath");
		
	}//service

}//end class
