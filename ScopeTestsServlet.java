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


@NoArgsConstructor
@WebServlet("/ScopeTests")
public class ScopeTestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
    	
    	ServletContext appScope = req.getServletContext(); 	//App.Scope관리객체
    	HttpSession sessionScope = req.getSession();		//Session Scope관리객체
    	HttpServletRequest reqScope = req;					//Request Scope관리객체
    	
//    	---------------------------------------------------
    	
    	String sharedData = "__SHARED_DATA__";				//공유데이터 

//    	---------------------------------------------------
    	
    	//1. Application Scope
//    	appScope.setAttribute("APP_SCOPE", sharedData);
    	
    	//2. Session Scope
//    	sessionScope.setAttribute("SESSION_SCOPE", sharedData);
    	
    	//3. Request Scope
    	reqScope.setAttribute("REQUEST_SCOPE", sharedData);

//    	---------------------------------------------------
    	res.setContentType("text/html; charset=utf-8");
    	
    	@Cleanup
    	PrintWriter out = res.getWriter();
    	
    	//특정 공유 영역에서 얻어낸 공유데이터 출력

//    	out.println("<h1>APP.SCOPE에 공유데이터를 올려 놓았습니다.</h1>");
//    	out.println("<h1>SESSION.SCOPE에 공유데이터를 올려 놓았습니다.</h1>");
    	out.println("<h1>REQUEST.SCOPE에 공유데이터를 올려 놓았습니다.</h1>");
    	
    	
    	
    	
    	out.flush();
	}//service

}//end class
