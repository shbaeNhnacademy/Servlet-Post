package com.nhnacademy.controller;

import com.nhnacademy.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAddController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //id,pwd,name,profile 로 들어온 데이터를 userRepository 받아서 setAtrrbute 하기
        return null;
    }
}
