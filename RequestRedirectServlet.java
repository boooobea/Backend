package org.zerock.myapp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("/RequestRedirect")
public class RequestRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		//1. 비즈니스 데이터(=Model)를 request scope 공유데이터에 영역에 바인딩
		//req.scope -- req.scope 파괴되어 공유 불가 
//		req.setAttribute("name", "홍길동");
//		req.setAttribute("address", "서울");
		
		//session.scope -- session scope는 공유 가능 
//		HttpSession sess = req.getSession();
//		sess.setAttribute("name", "홍길동");
//		sess.setAttribute("address", "서울");
		 
		//Step2. Redirect 응답 전송 
		URLEncoder.encode("문자열", "utf-8");

		//URLEncoder / URLDecoder 
		//URLEncoding --> URL 에는 기본적으로 다국어 문자 및 whitespace(공백, 탭)를 허용하지 않음
		//허용하는 형태로 바꾸어 주어야 한다.
		String queryStr = "?name="+URLEncoder.encode("홍길", "utf-8")+
						"&address="+URLEncoder.encode("서울2", "utf-8");
		log.info("\t+queryStr : {}", queryStr);                          
		 
		res.sendRedirect("/ResponseRedirect"+queryStr);
//		res.sendRedirect("/ResponseRedirect");
		
		//Redirect일때 req.scope로 데이터 전송시 파괴되어 전송되지 않고 
		//session.scope의 경우 business service에 맞지 않기 때문에 parameter로 전달
		
		log.info("Succesed to send a Response to the web browser.");
		
	}//service

}//end class
