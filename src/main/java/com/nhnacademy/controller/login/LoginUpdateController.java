package com.nhnacademy.controller.login;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class LoginUpdateController implements Command {

    private static String redirectLoginForm = "redirect:/login/loginForm.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = Optional.ofNullable(req.getParameter("id")).orElse("");
        String pwd =  Optional.ofNullable(req.getParameter("pwd")).orElse("");

        User admin = (User) req.getServletContext().getAttribute("admin");
        //
        Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getServletContext().getAttribute("sessionMap");


        if (admin.getId().equals(id) && admin.getPassword().equals(pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("admin", id);
            sessionMap.put(id, session);
            return "redirect:/login.do";
        }


        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        Map<String, String> collect = userRepository.getUsers()
                .stream()
                .collect(Collectors.toMap(User::getId, User::getPassword));


        // TODO iterator 로 containsKey가 동작해서 느릴수도
        if (collect.containsKey(id) && collect.containsValue(pwd)) {
            // id는 존재함
            for (Map.Entry<String, String> entry : collect.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals(id) && value.equals(pwd)) {
                    HttpSession session = req.getSession();
//                    session.setAttribute(id, id);
                    session.setAttribute("id", id);
                    sessionMap.put(id, session);
                    return "redirect:/login.do";
                }
            }

        }

        // 허용되지 않은 사용자
        return redirectLoginForm;
    }
}
