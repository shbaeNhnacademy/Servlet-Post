package com.nhnacademy.controller.user;

import com.nhnacademy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;

public class UserChangesCheckController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ArrayList<String> list = Collections.list(req.getParameterNames());
        if (list.size() > 2 || list.size() == 0) {
            return "/admin/userList.jsp";
        }else{
            String id = list.get(0);
            req.setAttribute("id", id);
            return "/admin/modifyUser.jsp";
        }

    }
}
