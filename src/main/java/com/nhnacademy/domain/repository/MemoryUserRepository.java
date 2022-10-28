package com.nhnacademy.domain.repository;

import com.nhnacademy.domain.user.User;
import com.nhnacademy.exception.SameUserIdException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepository {
    private Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override

    public void add(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new SameUserIdException(user.getId() + "는 이미 존재하는 id 입니다");
        }
        userMap.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new SameUserIdException(user.getId() + "는 이미 존재하는 id 입니다");
        }
        userMap.put(user.getId(), user);
    }

    @Override
    public User remove(String id) {
        return userMap.remove(userMap.get(id).getId());
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

}
