package com.nhnacademy.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.domain.post.ConcretePost;
import com.nhnacademy.domain.repository.*;
import com.nhnacademy.domain.user.Admin;
import com.nhnacademy.domain.user.GeneralUser;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebListener
public class WebAppListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("locale", "en");



        UserRepository userRepository = new JsonUserRepository(servletContext);
        PostRepository postRepository = new JsonPostRepository(servletContext);

        if (!(userRepository instanceof JsonUserRepository)) {
            Admin admin = new Admin("admin","12345","관리자");
            servletContext.setAttribute("admin", admin);
        }


        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("postRepository", postRepository);

        String visitCountFileName = servletContext.getInitParameter("visitCountFileName");
        String filePath = "/WEB-INF/classes/" + visitCountFileName;

        int visitCount = 0;

        try (DataInputStream dataInputStream = new DataInputStream(servletContext.getResourceAsStream(filePath))) {
            visitCount = Optional.of(dataInputStream.readInt()).orElse(0);
        } catch (IOException e) {
            log.error("", e);
        }
        servletContext.setAttribute("visitCount", visitCount);

        Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();
        servletContext.setAttribute("sessionMap", sessionMap);


        log.info("Listener Start count : {}", visitCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String visitCountFileName = servletContext.getInitParameter("visitCountFileName");
        String filePath = "/WEB-INF/classes/" + visitCountFileName;

        int visitCount = (int) servletContext.getAttribute("visitCount");

        try(OutputStream os = Files.newOutputStream(Paths.get(servletContext.getResource(filePath).toURI()));
            DataOutputStream dos = new DataOutputStream(os);)
        {
            dos.writeInt(visitCount);
        } catch (IOException e) {
            log.error("",e);
        } catch (URISyntaxException e) {
            log.error("",e);
        }

        UserRepository userRepository = (UserRepository) servletContext.getAttribute("userRepository");

    }

}
