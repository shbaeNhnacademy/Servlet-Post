package com.nhnacademy.controller.admin;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class UserModifyController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // userList.jsp에서 넘어온 check 박스 관련한 데이터를 getParameter로 받아서
        // 삭제 혹은 수정 필요
        // 수정시 체크박스 데이터 두개면 1개만 골라달라는 메시지와 함께 다시 선택 userList.jsp로 이동

        ArrayList<String> list = Collections.list(req.getParameterNames());
        if (list.size() > 2) {
            return "/admin/userList.jsp";
        }else{
            String id = list.get(0);
            req.setAttribute("id", id);
            return "/admin/modifyUser.jsp";
        }

    }
}
