package com.ajax.test.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//jsp와 servlet를 브라우저에 출력할때 한글이 깨지는걸 이 filter를 거치게해줘서 안깨지게 해줌
//서버에서 요청을하면 응답filter를 거치고 서블릿으로 감
//하나의 서블릿만 있다면 url패턴에 하나만적으면됨
//여러개는 "/*을 써서 모두를 바라보게해줌
//jsp,css등 이미지는 문자가 아니기때문에 filter을 거칠필요가없음 
@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {

 public TestFilter() { // doFilter를 실행하기위해 메모리생성을해야해서 1번 서버켜질때 1번만 호출(생성자)

 }

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)// url패턴이 맞으면 언제나 호출됨 3등
       throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest)request;
    String url = req.getRequestURI();
    int idx = url.lastIndexOf(".");
    if(idx != -1) {
       String ext = url.substring(idx+1,url.length());
       if("html".equals(ext)) {
    	   chain.doFilter(request, response);
          return;
       }
    }
    response.setContentType("text/html; charset=UTF-8");
    request.setCharacterEncoding("utf-8");
    chain.doFilter(request, response);
 }

 public void init(FilterConfig fConfig) throws ServletException {// 초기화라는뜻인데 이러나저러나 생성자 선언되고(1번) 호출됨 2등
    String excludePatterns = fConfig.getInitParameter("excludes");
    System.out.println(excludePatterns);
 }

 public void destroy() { // 서버꺼질때 호출됨 4등

 }

}