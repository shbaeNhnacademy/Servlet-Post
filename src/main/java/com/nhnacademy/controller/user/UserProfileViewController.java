package com.nhnacademy.controller.user;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfileViewController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String id_do = split[split.length - 1];
        String userId = id_do.substring(0, id_do.length() - 3);

        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        User user = userRepository.getUser(userId);
        req.setAttribute("user",user);

        return "/user/viewProfile.jsp";
    }
}
