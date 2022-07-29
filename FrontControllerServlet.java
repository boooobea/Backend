package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.BizProcessExeption;
import org.zerock.myapp.service.DeleteService;
import org.zerock.myapp.service.InsertService;
import org.zerock.myapp.service.SelectService;
import org.zerock.myapp.service.Service;
import org.zerock.myapp.service.UnknownService;
import org.zerock.myapp.service.UpdateService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req,res)invoked.");
		
//		---------------------------------------
//		step 1. 전송 파라미터를 DTO객체로 수집
//		---------------------------------------
		
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String sal	 = req.getParameter("sal");
		String deptno = req.getParameter("deptno");
		
		//수집된 각 전송 파라미터를 DTO객체에 저장
		//DTO 객체는 웹3계층(표현/비즈니스/영속계층) 뒤로 전달되면서 사용된다.
		EmpDTO dto = new EmpDTO();
		
		if( empno !=null && !"".equals(empno)) {
			dto.setEmpno(Integer.parseInt(empno));
		}
		dto.setEname(ename);
		
		if( sal !=null && !"".equals(sal)) {
			dto.setSal(Double.parseDouble(sal));
		}
		if( deptno !=null && !"".equals(deptno)) {
			dto.setDeptno(Integer.parseInt(deptno));
		}
		
		//Request Scope 공유 데이터 영역에 DTO 객체를 속성으로 바인딩
		//***모든 service 객체의 비지니스 로직 수행에 필요한 전송 파라미터를
		//전달해주는 DTO객체를 또 다른 공유 데이터 영역인 Request scope를 통해 전달(HttpServletRequest)
		req.setAttribute(Service.DTO, dto);
		
//		모든 공유되어있는 데이터들을 확인할 수 있다(Enumeration)각 scope마다 있음
//		req.getAttributeNames();
		
//		---------------------------------------
//		step 2. 요청 URI, command(요청유형) 결정
//		---------------------------------------
		String command = req.getRequestURI();
		log.info("\t+3. command : {}", command);
		
//		---------------------------------------
//		step 3. command에 따라 적합한 비지니스 서비스 객체 선택, 로직 수행 
//		---------------------------------------
		try {
				
			 // command(요청유형)에 따라, 각 요청을 처리하는 서비스 객체의 생성 및
	         // 비지니스 로직 수행(execute 메소드)
	         // * 주의 * : 비지니스 로직 수행 결과 데이터는, 공유데이터 영역인 
	         //            "Application Scope"(accessed by ServletContext)에 바인딩
			switch(command) {
				case "/insert.do": 	new InsertService().execute(req, res);break;
				case "/delete.do":	new DeleteService().execute(req, res);break;
				case "/update.do":	new UpdateService().execute(req, res);break;
				case "/select.do":	new SelectService().execute(req, res);break;
				default:			new UnknownService().execute(req, res);break;
	
			}//switch
			
		} catch ( BizProcessExeption e) { //예외발생으로 또다른 예외발생 
			throw new ServletException(e); 
			
		} //try-catch 
		
//		---------------------------------------
//		step 4. command 별, 비지니스 로직 수행 결과 데이터를 이용하여 응답 문서 생성 
//		---------------------------------------
				
//		res.setContentType("text/html; charset=utf-8");
//				
//		@Cleanup
//		PrintWriter out = res.getWriter();
//		out.println("<html><head></head><body>");
//		
//		//각 command 별로 수행되는 service 객체의 execute 메소드의 수행결과 데이터를
//		//"Application Scope"에서 얻어서, 응답문서 생성에 사용
//		ServletContext sc = getServletContext();
//		Object bizResult = sc.getAttribute(Service.BIZ_RESULT);
//		
//		out.println("<p>"+bizResult+"</p>");
//				
//		out.println("</body></html>");
//		out.flush();
//		
//		//응답 문서 생성이 끝나면 application scope에 공유된 
//		//서비스 객체의 비즈니스 메모리를 삭제 (속성 unbinding)
//		sc.removeAttribute(Service.BIZ_RESULT);
		
//		--------
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/View");
		dispatcher.forward(req, res);
		
		log.info("Forwarded request into /View");
		
	}//service

}//end class
