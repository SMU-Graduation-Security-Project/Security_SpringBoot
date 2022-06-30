package com.EmperorPenguin.SangmyungBank.baseUtil.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        System.out.println("Filter1");
        System.out.println(req.getHeader("Authorization"));
        chain.doFilter(request, response);
    }
}
