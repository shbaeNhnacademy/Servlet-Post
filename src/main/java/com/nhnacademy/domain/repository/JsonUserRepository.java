package com.nhnacademy.domain.repository;

import com.nhnacademy.domain.user.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonUserRepository implements UserRepository{

    private Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public void add(User user) {

    }

    @Override
    public void modify(User user) {

    }

    @Override
    public User remove(String id) {
        return null;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
