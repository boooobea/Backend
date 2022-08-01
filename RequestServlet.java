package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("/Request")

//MVC 패턴에서 Controller 역할 
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
    	log.trace("service(req, res) invoked.");
    	
    	//Step 1. 비즈니스 로직 수행 
    	
    	//Step 2. 비즈니스 데이터(=Model)를 request scope 공유데이터에 영역에 바인딩 
    	req.setAttribute("name", "홍길동");
    	req.setAttribute("address", "서울");
    	
    	//Step 3. 응답할 웹 컴포넌트에 요청을 위임(request forwarding) == View
    	RequestDispatcher dispatcher =  req.getRequestDispatcher("/Response");
    	dispatcher.forward(req, res);
    	
    	log.info("Forwarded request to the /Response");
    	
	}//service

}//end class
