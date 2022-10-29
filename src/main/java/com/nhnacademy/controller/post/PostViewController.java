package com.nhnacademy.controller.post;

import com.nhnacademy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostViewController  implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String id_do = split[split.length - 1];
        long postId = Long.parseLong(id_do.substring(0, id_do.length() - 3));


        return "/post/postList.jsp";
    }
}
