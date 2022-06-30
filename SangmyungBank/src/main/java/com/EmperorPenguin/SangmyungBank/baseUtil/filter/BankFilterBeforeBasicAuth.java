package com.EmperorPenguin.SangmyungBank.baseUtil.filter;


import javax.servlet.*;
import java.io.IOException;

public class BankFilterBeforeBasicAuth implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // ID, Pw가 정상적으로 들어와서 로그인이 완료돠ㅣ면 토큰을 만들어주고 그것을 응답을 해준다.
        // 요청할 때마다 header에 Authorization에 값으로 토큰을 가져오면
        // 그때 이 토큰이 내가 만들었는지 검증하면 된다.
        System.out.println("Bank Security Filter Start");
        chain.doFilter(request, response);
    }
}
