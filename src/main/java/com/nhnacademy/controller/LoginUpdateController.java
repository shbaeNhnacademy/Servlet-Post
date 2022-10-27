package com.nhnacademy.controller;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.GeneralUser;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class LoginUpdateController implements Command {

    private static String redirectLoginForm = "redirect:/loginForm.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = Optional.ofNullable(req.getParameter("id")).orElse("");
        String pwd =  Optional.ofNullable(req.getParameter("pwd")).orElse("");


        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        Map<String, String> collect = userRepository.getUsers()
                .stream()
                .collect(Collectors.toMap(User::getId, User::getPassword));

        if (collect.containsKey(id)) {
            // id는 존재함
            if (collect.containsValue(pwd)) {
                for (String key : collect.keySet()) {
                    if (key.equals(id) && collect.get(key).equals(pwd)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("id_" + id, id);
                        return "redirect:/login.do";
                    }
                }
            }
        }

        // 허용되지 않은 사용자
        return redirectLoginForm;
    }
}
