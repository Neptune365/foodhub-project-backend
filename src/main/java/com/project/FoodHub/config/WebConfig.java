package com.project.FoodHub.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.setHeader("Access-Control-Allow-Origin", "https://656f4e404ec6a10842152769--inquisitive-pegasus-56ce2b.netlify.app");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, HEAD, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, credential, X-XSRF-TOKEN");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
          filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}
