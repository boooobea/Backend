package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet(name = "Hello2", urlPatterns = { "/hello2" })
public class Hello2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");	
	
		HttpSession sess = req.getSession();
		log.info("\t+sess : {}", sess.getId() );
		
		PrintWriter out = res.getWriter();
		out.println("<h1>World!!!22</h1>");
		
	
		out.flush();
		out.close();
	}// service

}
