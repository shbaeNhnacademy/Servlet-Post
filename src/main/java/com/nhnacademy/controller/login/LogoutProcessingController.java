package com.nhnacademy.controller.login;

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
            Object admin = session.getAttribute("admin");
            Object id = session.getAttribute("id");
            if (Objects.nonNull(admin)) {
                session.setAttribute("admin", "");
            }else if(Objects.nonNull(id)){
                session.setAttribute("id", "");
            }
            session.invalidate();
        }
        return "redirect:/login.do";
    }
}
