package com.nhnacademy.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@WebFilter(filterName = "counterFilter", urlPatterns = "/*",initParams = {
        @WebInitParam(name = "include-urls",value = "/posts/\n" +
                "/users/\n")
})
public class CounterFilter implements Filter {
    private Set<String> includedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("include-urls");

        includedUrls = Arrays.stream(initParameter.split("\n"))
                .collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean isIncludeUrl = false;
        for (String includedUrl : includedUrls) {
            if (request.getRequestURI().contains(includedUrl)) {
                isIncludeUrl = true;
                break;
            }
        }
        if (isIncludeUrl) {
            ServletContext servletContext = request.getServletContext();
            int visitCount = (int) Optional.ofNullable(servletContext.getAttribute("visitCount")).orElse(0);

            servletContext.setAttribute("visitCount", ++visitCount);

            filterChain.doFilter(request, response);
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
