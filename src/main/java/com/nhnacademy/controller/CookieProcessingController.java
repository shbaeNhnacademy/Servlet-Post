package com.nhnacademy.controller;

import com.nhnacademy.command.Command;
import com.nhnacademy.listener.WebAppListener;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieProcessingController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Cookie localeCookie = WebAppListener.getCookie(req, "locale");
        String locale = localeCookie.getValue();
        req.getServletContext().setAttribute("locale", locale);

        return "/index.jsp";
    }
}
