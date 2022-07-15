package org.zerock.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.InputBuffer;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("/ContextFile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service( req, res ) invoked.");
		
//		String path = "/WEB-INF/testFile.txt";
		String path = "/WEB-INF/web.xml";
		
		ServletContext sc = this.getServletContext();
		
		@Cleanup
		InputStream is = sc.getResourceAsStream(path);
		@Cleanup
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
//		---
		
		res.setContentType("text/html; charset=utf-8");
		
		@Cleanup
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<p>");
		
		String line = null;
		
		while( (line = br.readLine()) != null ) {		//EOF:null
			out.print(line+"<br>");
			
		}//while
		
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}//end service

}//end class
