package com.nhnacademy.servlet;

import com.nhnacademy.command.Command;
import com.nhnacademy.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {
            Command command = resolveServlet(req.getServletPath(), req.getMethod());
            String view = command.execute(req, resp);

            log.info("Path : {} , ,method : [{}], VIEW : {}", req.getServletPath(), req.getMethod(), view);

            if (view.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
                requestDispatcher.include(req, resp);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    private Command resolveServlet(String commandPath,String method) {
        Command command = null;

        if ("/posts.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new PostRegisterController();
        }else if ("/login.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new LoginProcessingController();
        }else if ("/login.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new LoginUpdateController();
        }else if ("/logout.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new LogoutProcessingController();
        }else if ("/read-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieProcessingController();
        }else if ("/set-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieUpdateController();
        }else if ("/read-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieProcessingController();
        }else if ("/set-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieUpdateController();
        }
        return command;
    }
}
