<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.*"%>
<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String saveDirectory = application.getRealPath("/upload");	// 실제 파일이 저장된 경로
String saveFilename = request.getParameter("sName");		// 실제 서버에 저장된 파일명
String originalFilename = request.getParameter("oName");	// 사용자가 첨부한 원본 파일명

try {
	File file = new File(saveDirectory, saveFilename);	// 경로와 서버의 파일명으로 File 객체 생성
	InputStream inStream = new FileInputStream(file);	// 입력 스트림 객체 생성
	
	// 한글파일명 깨짐 방지
	String client = request.getHeader("User-Agent");	// request 내 User-Agent : 사용자의 브라우저 정보
	if (client.indexOf("WOW64") == -1) {		// WOW64 문자열이 포함안된 경우 -> IE 제외한 브라우저
		originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
	} 
	else {		// WOW64 문자열이 포함된 경우 -> IE
		originalFilename = new String(originalFilename.getBytes("KSC5601"), "ISO-8859-1");
	}
	
	// 파일 다운로드용 응답헤더 설정 (브라우저 헤더 정보 저장)
	response.reset();
	response.setContentType("application/octet-stream");	// 문서의 타입 변경
	response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");	// 업로드 했던 원본 파일명으로 변경
	response.setHeader("Content-Length", "" + file.length() );	// 파일의 용량
	
	// 출력 스트림 초기화
	out.clear();
	
	// response 내장 객체로부터 새로운 출력 스트림 생성
	OutputStream outStream = response.getOutputStream();
	
	// 출력 스트림에 파일 내용 출력
	byte b[] = new byte[(int)file.length()];		// 파일의 용량만큼 배열의 길이 설정
	int readBuffer = 0;
	while ( (readBuffer = inStream.read(b)) > 0 ) {
		outStream.write(b, 0, readBuffer);
	}
	
	// 입/출력 스트림 닫음
	inStream.close();
	outStream.close();
}
catch (FileNotFoundException e) {
	System.out.println("파일을 찾을 수 없습니다");
}
catch (Exception e) {
	System.out.println("예외가 발생하였습니다.");
}

%>