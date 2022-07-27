package org.zerock.myapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@WebServlet("*.do")
public class FrontCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		String requestURL = req.getRequestURL().toString();
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		log.info("\t + 1. requestURL : {}", requestURL);
		log.info("\t + 2. requestURI : {}", requestURI);
		log.info("\t + 3. command : {}", command);
		log.info("\t + 4. contextPath : {}", contextPath);
	}//service

}//end class
