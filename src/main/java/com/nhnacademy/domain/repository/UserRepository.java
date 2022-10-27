package com.nhnacademy.domain.repository;

import com.nhnacademy.domain.user.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    User remove(String id);

    User getUser(String id);
    List<User> getUsers();
}

