package com.nhnacademy.domain.user;

public class LoginUser {
    public static int sessionCount = 0;

    public static int getSessionCount() {
        return sessionCount;
    }

    public static void setSessionCount(int sessionCount) {
        LoginUser.sessionCount = sessionCount;
    }
}
