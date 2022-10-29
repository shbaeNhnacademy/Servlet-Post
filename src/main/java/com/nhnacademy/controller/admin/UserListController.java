package com.nhnacademy.controller.admin;

import com.nhnacademy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/admin/userList.jsp";
    }
}
