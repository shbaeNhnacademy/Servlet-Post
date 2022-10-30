package com.nhnacademy.domain.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.domain.user.Admin;
import com.nhnacademy.domain.user.GeneralUser;
import com.nhnacademy.domain.user.User;
import com.nhnacademy.exception.SameUserIdException;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JsonUserRepository implements UserRepository {
    private Map<String, User> userMap = new ConcurrentHashMap<>();
    private ServletContext servletContext;

    private final static String userJsonFileName = "users.json";
    private final static String adminJsonFileName = "admin.json";

    public JsonUserRepository(ServletContext servletContext) {
        this.servletContext = servletContext;
        Admin admin = generateAdminFromJson(adminJsonFileName);
        this.servletContext.setAttribute("admin", admin);

        generateUsersFromJson(userJsonFileName);
    }

    private Admin generateAdminFromJson (String jsonFileName) {
        Admin admin = null;
        String filePath = "/WEB-INF/classes/json/" + jsonFileName;

        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = Files.newInputStream(Paths.get(servletContext.getResource(filePath).toURI()));) {
            Map<String, String> map = mapper.readValue(inputStream, Map.class);
            admin = new Admin(map.get("id"), map.get("password"), map.get("name"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    private void generateUsersFromJson(String jsonFileName) {

        String filePath = "/WEB-INF/classes/json/"+ jsonFileName;
        ObjectMapper mapper = new ObjectMapper();
        try(InputStream inputStream = Files.newInputStream(Paths.get(servletContext.getResource(filePath).toURI()));) {
            List<Map<String,String>> mapList = mapper.readValue(inputStream, List.class);
            for (Map<String, String> map : mapList) {
                GeneralUser user = new GeneralUser(map.get("id"), map.get("password"), map.get("name"));
                if (Objects.nonNull(map.get("profileFileName"))) {
                    user.setProfileFileName(map.get("profileFileName"));
                }
                userMap.put(map.get("id"), user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUsersToJson(String jsonFileName) {
        String filePath = "/WEB-INF/classes/json/"+ jsonFileName;
        ObjectMapper mapper = new ObjectMapper();
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(servletContext.getResource(filePath).toURI()));) {
            mapper.writeValue(outputStream, userMap.values().toArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new SameUserIdException(user.getId() + "는 이미 존재하는 id 입니다");
        }
        userMap.put(user.getId(), user);
        saveUsersToJson(userJsonFileName);
    }

    @Override
    public void modify(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new SameUserIdException(user.getId() + "는 이미 존재하는 id 입니다");
        }
        userMap.put(user.getId(), user);
        saveUsersToJson(userJsonFileName);
    }

    @Override
    public User remove(String id) {
        User remove = userMap.remove(userMap.get(id).getId());
        saveUsersToJson(userJsonFileName);
        return remove;
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
