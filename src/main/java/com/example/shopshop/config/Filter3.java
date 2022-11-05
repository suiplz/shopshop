package com.example.shopshop.config;

import javax.servlet.*;
import java.io.IOException;

public class Filter3 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터3");
        chain.doFilter(request, response);

    }
}
