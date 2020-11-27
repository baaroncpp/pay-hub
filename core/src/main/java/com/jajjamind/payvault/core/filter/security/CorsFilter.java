package com.jajjamind.payvault.core.filter.security;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akena
 * 17/11/2020
 * 17:04
 **/
@Configuration
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse filterResponse = (HttpServletResponse) response;

        filterResponse.setHeader("Access-Control-Allow-Origin", "*");
        filterResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        filterResponse.setHeader("Access-Control-Allow-Headers", "*");
        filterResponse.setHeader("Access-Control-Allow-Credentials", "true");
        filterResponse.setHeader("Access-Control-Max-Age", "180");
        chain.doFilter(request, filterResponse);
    }
}
