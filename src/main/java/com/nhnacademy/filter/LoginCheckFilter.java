package com.nhnacademy.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "exclude-urls",value = "loginForm.jsp\n" +
                "/\n" +
                "/login.do\n" +
                "/index.jsp\n" +
                "/foods.do\n" +
                "/set-cookie.do\n" +
                "/read-cookie.do\n")
})
public class LoginCheckFilter implements Filter {

    private Set<String> excludedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("exclude-urls");

        excludedUrls = Arrays.stream(initParameter.split("\n"))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        log.info("session : {}, path : {} , request : {} ",session,request.getServletPath(),request.getMethod());
        if (Objects.isNull(session)) {
            if (excludedUrls.contains(request.getRequestURI())) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("/login.do");
                rd.forward(request, response);
            }

        }else{
            //가던길 가세요?
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
