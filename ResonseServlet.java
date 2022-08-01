package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/Response")
public class ResonseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
	  
		//Step 1. 공유 데이터 영역(req.scope)에서 model 얻어내기 
		String name = (String)req.getAttribute("name");
		String address = (String)req.getAttribute("address");
		
		log.debug("model : {}, {}",name,address);
		
		
		//Step 2. model을 이용한 응답문서의 생성, 전송
		res.setContentType("text/html; charset=utf-8");
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html><head></head><body>");
		
		out.println("<h1>/Response</h1>");
		out.println("<hr>");
		out.println("<h2>name : " + name + "</h2>");
		out.println("<h2>address : " + address + "<h2>");
		
		out.println("</body></html>");
		out.flush();
	}//service

}//end class
