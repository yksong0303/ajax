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

//jsp�� servlet�� �������� ����Ҷ� �ѱ��� �����°� �� filter�� ��ġ�����༭ �ȱ����� ����
//�������� ��û���ϸ� ����filter�� ��ġ�� �������� ��
//�ϳ��� ������ �ִٸ� url���Ͽ� �ϳ����������
//�������� "/*�� �Ἥ ��θ� �ٶ󺸰�����
//jsp,css�� �̹����� ���ڰ� �ƴϱ⶧���� filter�� ��ĥ�ʿ䰡���� 
@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {

 public TestFilter() { // doFilter�� �����ϱ����� �޸𸮻������ؾ��ؼ� 1�� ���������� 1���� ȣ��(������)

 }

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)// url������ ������ ������ ȣ��� 3��
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

 public void init(FilterConfig fConfig) throws ServletException {// �ʱ�ȭ��¶��ε� �̷��������� ������ ����ǰ�(1��) ȣ��� 2��
    String excludePatterns = fConfig.getInitParameter("excludes");
    System.out.println(excludePatterns);
 }

 public void destroy() { // ���������� ȣ��� 4��

 }

}