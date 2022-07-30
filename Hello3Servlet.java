package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet(
		name = "Hello3Servlet", 
		urlPatterns = { "/xxx", "/yyy" } //String[] urlPatterns()
		)
public class Hello3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.trace("service( req, res )s invoked.");
		
		//HTTP request 메세지로부터, 전송파라미터를 얻어냄 
//		String name = req.getParameter("name"); 	//전송파라미터의 값이 1개일 때 사용 
//		String age = req.getParameter("age");
//		String hobby = req.getParameter("hobby");
//		String[] hobbys = req.getParameterValues("hobby");	//값이 여러개를 배열형태로 받음 
		
//		=============================
		//배열이 아닌 열거타입으로 받아서 사용
//		Enumeration<String> paramNames = req.getParameterNames();
//		
//		while( paramNames.hasMoreElements()) {
//			String name = paramNames.nextElement();
//			log.info("\t+name : {}", name);
//		}//while 
//		=============================
//		@Cleanup("clear") : 허용 불가 
		Map<String, String[]> map = req.getParameterMap();
		
		log.info("\t + map:{}, hobby:{}", map, map.get("hobby"));
		
		
//		=============================
		PrintWriter out = res.getWriter();
		out.println("<h1>World!!!22</h1>");
		
		out.flush();
		out.close();
		
	}//service

}// end class
