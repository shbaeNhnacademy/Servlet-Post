package com.nhnacademy.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {
    private int count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session={}",++count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session={}",--count);

    }
}
