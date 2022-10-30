package com.nhnacademy.domain.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.domain.user.User;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class JsonUserRepository implements UserRepository {
    private Map<String, User> userMap = new ConcurrentHashMap<>();

    public JsonUserRepository() {
        getJsonFile();

    }

    private void getJsonFile() {
        String jsonFileName = "admin.json";
        String filePath = "/WEB-INF/classes/json/" + jsonFileName;
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File(filePath);
            System.out.println("jsonFile = " + jsonFile);
            List<Map<String, String>> mapList = mapper.readValue(jsonFile, List.class);
            System.out.println("mapList = " + mapList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
