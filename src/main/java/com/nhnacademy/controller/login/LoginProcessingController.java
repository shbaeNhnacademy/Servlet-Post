package com.nhnacademy.controller.login;

import com.nhnacademy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginProcessingController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
            return "/login/loginForm.jsp";
        } else {
            if (!Objects.isNull(session.getAttribute("admin"))) {
                return "/admin/admin.jsp";
            }
            return "/";
        }
    }
}
