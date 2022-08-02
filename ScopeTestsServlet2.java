package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@WebServlet("/ScopeTests2")
public class ScopeTestsServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
    	
    	ServletContext appScope = req.getServletContext(); 	//App.Scope관리객체
    	HttpSession sessionScope = req.getSession();		//Session Scope관리객체
    	HttpServletRequest reqScope = req;					//Request Scope관리객체
    	
//    	---------------------------------------------------
    	
    	//1. Application Scope
//    	String getSharedDataFromAppScope = (String)appScope.getAttribute("APP_SCOPE");

    	//2. Session Scope
//    	String getSharedDataFromSessionScope = (String)sessionScope.getAttribute("SESSION_SCOPE");
    	
    	//3. Request Scope
    	String getSharedDataFromRequestScope = (String)reqScope.getAttribute("REQUEST_SCOPE");
    	
//    	---------------------------------------------------
    	res.setContentType("text/html; charset=utf-8");
    	
    	@Cleanup
    	PrintWriter out = res.getWriter();
    	
    	//특정 공유 영역에서 얻어낸 공유데이터 출력
//    	String data = getSharedDataFromAppScope;
//    	String data = getSharedDataFromSessionScope;
    	String data = getSharedDataFromRequestScope;

    	out.println("<h1>"+data+"</h1>");
    	
    	out.flush();
	}//service

}//end class
