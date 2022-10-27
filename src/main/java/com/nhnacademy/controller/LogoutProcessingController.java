package com.nhnacademy.controller;

import com.nhnacademy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutProcessingController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (!Objects.isNull(session)) {
            session.invalidate();
        }
//        return "/loginForm.jsp";
        return "redirect:/login.do";
    }
}
