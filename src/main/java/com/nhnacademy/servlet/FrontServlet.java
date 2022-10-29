package com.nhnacademy.servlet;

import com.nhnacademy.command.Command;
import com.nhnacademy.controller.user.*;
import com.nhnacademy.controller.cookie.CookieProcessingController;
import com.nhnacademy.controller.cookie.CookieUpdateController;
import com.nhnacademy.controller.login.LoginProcessingController;
import com.nhnacademy.controller.login.LoginUpdateController;
import com.nhnacademy.controller.login.LogoutProcessingController;
import com.nhnacademy.controller.post.PostDeleteController;
import com.nhnacademy.controller.post.PostModifyController;
import com.nhnacademy.controller.post.PostRegisterController;
import com.nhnacademy.controller.post.PostIdViewController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@MultipartConfig(
        location = "/tmp/",
        maxFileSize = -1L,
        maxRequestSize = -1L,
        fileSizeThreshold = 1024
)
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

        if ("/login.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new LoginProcessingController();
        } else if ("/login.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new LoginUpdateController();
        } else if ("/logout.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new LogoutProcessingController();
        } else if ("/read-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieProcessingController();
        } else if ("/set-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieUpdateController();
        } else if ("/read-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieProcessingController();
        } else if ("/set-cookie.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new CookieUpdateController();
        } else {
            command = businessLogicCommand(commandPath, method);
        }
        return command;
    }

    private Command businessLogicCommand(String commandPath,String method) {
        Command command = null;

       if ("/users.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserAddController();
        } else if ("/users.do".equals(commandPath) && "GET".equalsIgnoreCase(method)) {
            command = new UserListController();
        }else if ("/users/modify.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserChangesCheckController();
        }else if ("/users/delete.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserDeleteController();
        }else if ("/users/change.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserModifyController();
        }else  if ("/posts.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
           command = new PostRegisterController();
       }else if (commandPath.matches("^/users/[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.do$") && "GET".equalsIgnoreCase(method)) {
           command = new UserProfileViewController();
       } else if (commandPath.matches("^/posts/[0-9]*.do$") && "GET".equalsIgnoreCase(method)) {
           command = new PostIdViewController();
       } else if ("/posts/modify.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
           command = new PostModifyController();
       }else if ("/posts/delete.do".equals(commandPath) && "POST".equalsIgnoreCase(method)) {
           command = new PostDeleteController();
       }

       return command;
    }
}
