package com.nhnacademy.controller;

import com.nhnacademy.command.Command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String locale = req.getParameter("locale");
        Cookie cookie = new Cookie("locale", locale);
        cookie.setMaxAge(-1);

        resp.addCookie(cookie);

        return "redirect:/read-cookie.do";
    }
}
