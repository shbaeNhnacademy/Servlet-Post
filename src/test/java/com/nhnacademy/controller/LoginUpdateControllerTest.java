package com.nhnacademy.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginUpdateControllerTest {
    @Test
    void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("merge");
        when(request.getParameter("pwd")).thenReturn("1234");
        HttpSession session = request.getSession();
        when(request.getSession(true)).thenReturn(session);

        String s = "/users/nghn1";
        System.out.println(s.matches("^/users/[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.do$"));

//
//        LoginUpdateController controller = new LoginUpdateController();
//        String execute = controller.execute(request, response);
//
//        Assertions.assertThat(execute).isEqualTo("redirect:/login.do");
    }

}
