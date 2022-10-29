package com.nhnacademy.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginProcessingControllerTest {


    @Test
    void execute() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("merge");
        when(request.getParameter("pwd")).thenReturn("1234");


//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        new MyServlet().doPost(request, response);
//
//        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
//        writer.flush(); // it may not have been flushed yet...
//        assertTrue(stringWriter.toString().contains("My expected string"));
    }
}
