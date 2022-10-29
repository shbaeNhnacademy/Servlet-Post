package com.nhnacademy.controller.admin;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // userList.jsp에서 넘어온 check 박스 관련한 데이터를 getParameter로 받아서
        // 삭제 혹은 수정 필요
        // 수정시 체크박스 데이터 두개면 1개만 골라달라는 메시지와 함께 다시 선택 userList.jsp로 이동

        Map<String, HttpSession> sessionMap = (Map<String, HttpSession>) req.getServletContext().getAttribute("sessionMap");


        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String id = names.nextElement();
            // userRepository에서 삭제
            UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
            User remove = userRepository.remove(id);

            //파일 정리
            //TODO 삭제된 이용자의 이미지파일 삭제

            //세션 종료
            if (sessionMap.containsKey(id)) {
                HttpSession httpSession = sessionMap.remove(id);
                httpSession.removeAttribute(id);
                httpSession.invalidate();
            }
            log.info("remove : {} / rest : {}", remove, userRepository.getUsers());

        }

        return "/admin/userList.jsp";
    }
}
