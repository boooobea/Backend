package org.zerock.myapp;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/FileDownload")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		req.setCharacterEncoding("utf-8");

		
		//Step 1. 다운로드 받을 파일명을 전송파라미터로 획득 
		String fileName = req.getParameter("fileName");	//다운로드 요청 파일명
		log.info("\t+ fileName : {}", fileName);
		
		
		//Step 2. 실제 다른로드 처리 수행
		
		
		//	1) 다운로드 파일의 절대경로 생성
		final String uploadPath = "c:/temp/upload/"; //업로드시 저장될 위치
		final String downloadPath = uploadPath + fileName;	//다운받을 위치
		
		log.info("\t+ downloadPath :{}", downloadPath);

		
		//	2) MimeType(파일변환) 획득(다운받은 경로)
		ServletContext sc = this.getServletContext();
		
		String mimeType = sc.getMimeType(downloadPath);
			
			if(mimeType == null) {
				mimeType = "application/octet-stream";	//Well-Know extension이 아닐경우 최후의 수단
			}//if
			
		log.info("\t+ mineType : {}", mimeType);
		
		
		//	3) 다운로드 파일의 MimeType을 응답문서의 컨텐츠 유형으로 지정(***)
		res.setContentType(mimeType);
		
		
		//	4) 파일명이 다국어문자/공백이 포함되어 있을경우 파일명에 손상없이 나타나도록 인코딩처리
		//파일명 -> ASCII 문자만 포함한 형태로 변환
		//ASCII -> ISO-8859-1
		String endodedFileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
		log.info("\t + endodedFileName:{}", endodedFileName);
		
		
		// 	5) 응답문서의 헤더에 특수한 헤더를 추가함(필수항목***)
		//Content-Disposition 헤더는
		//가. 컨텐츠가 브라우저에 inline되어야 하는 웹페이지 자체의 일부인지 
		//나. attachment(첨부파일)로써 다운로드 되거나 로컬에 저장될 용도로 쓰이는 것인지 알려줌
		
		res.setHeader("Content-Disposition", "attachment; fileName=" + endodedFileName);
		
		
		// 	6) 다운로드 경로의 파일의 데이터를 읽어서, 응답메세지의 body(Payload)에 넣는다.
		
		
		// 	7) JAVA I/O API 
		
		@Cleanup
		FileInputStream fis = new FileInputStream(downloadPath); //바이트 입력 스트림
		@Cleanup
		ServletOutputStream sos = res.getOutputStream(); //응답메세지 출력(바이트 출력 스트림)
		
		byte[] buf = new byte[100];
		int readByte;	//읽어들인 바이트 사이즈 

		while((readByte = fis.read(buf)) != -1) {			//EOF(-1) 까지 반복
			sos.write(buf, 0, readByte);
			
		}//while
		
		sos.flush();
		
		
		
		
		
	}//service
}//end class
