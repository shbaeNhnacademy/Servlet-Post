package com.nhnacademy.listener;

import com.nhnacademy.domain.user.LoginUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {
    LoginUser loginUser = new LoginUser();
    private int count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        loginUser.setSessionCount(++count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        loginUser.setSessionCount(--count);
    }
}
