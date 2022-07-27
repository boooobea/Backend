package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("/ContextParamGet")
public class ContextParamGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service( req , res ) invoked.");

		ServletContext sc = this.getServletContext();		//Application Scope에 공유한 문자열을 얻어내기 
		Objects.requireNonNull(sc);
		
		//getAttribute type은 object -> casting 
		String jdbcDriver = (String)sc.getAttribute("driver");
		String savePath = (String)sc.getAttribute("savePath");
		
		log.info("jdbcDriver : {}", jdbcDriver);
		log.info("savePath : {}", savePath);
//		--- 
		
		res.setContentType("text/html; chatset=utf8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println(String.format("1. jdbcDriver : %s", jdbcDriver));
		out.println(String.format("2. savePath : %s", savePath));
		
		log.info("jdbcDriver : {}, savePath : {} ", jdbcDriver, savePath);
		out.flush();
		
		
	}//service

}//end class
