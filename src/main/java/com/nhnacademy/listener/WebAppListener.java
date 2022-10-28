package com.nhnacademy.listener;

import com.nhnacademy.domain.repository.MemoryPostRepository;
import com.nhnacademy.domain.repository.MemoryUserRepository;
import com.nhnacademy.domain.repository.PostRepository;
import com.nhnacademy.domain.repository.UserRepository;
import com.nhnacademy.domain.user.Admin;
import com.nhnacademy.domain.user.GeneralUser;
import com.nhnacademy.domain.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebListener
public class WebAppListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        // 관리자 생성 후 servletContext.속성에 set
        Admin admin = new Admin("admin","12345","관리자");
        servletContext.setAttribute("admin", admin);

        // repository(user/post) 두개 만들어서 servletContext.속성에 set
        UserRepository userRepository = new MemoryUserRepository();
        PostRepository postRepository = new MemoryPostRepository();

        // TODO 테스트 사용자 등록
        registerUsers(userRepository);

        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("postRepository", postRepository);

        //visitCount 불러오기
        String visitCountFileName = servletContext.getInitParameter("visitCountFileName");
        String filePath = "/WEB-INF/classes/" + visitCountFileName;

        int visitCount = 0;

        try (DataInputStream dataInputStream = new DataInputStream(servletContext.getResourceAsStream(filePath))) {
            visitCount = Optional.ofNullable(dataInputStream.readInt()).orElse(0);
        } catch (IOException e) {
            log.error("", e);
        }
        servletContext.setAttribute("visitCount", visitCount);

        //sessionMap 생성 및 context 속성에 set
        Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();
        servletContext.setAttribute("sessionMap", sessionMap);


        log.error("Listener Start count : {}", visitCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        //visitCount 저장
        String visitCountFileName = servletContext.getInitParameter("visitCountFileName");
        String filePath = "/WEB-INF/classes/" + visitCountFileName;

        int viewCount = (int) servletContext.getAttribute("viewCount");

        //TODO 주석빼고 방문횟수 저장해야함
//        try(OutputStream os = Files.newOutputStream(Paths.get(servletContext.getResource(filePath).toURI()));
//            DataOutputStream dos = new DataOutputStream(os);)
//        {
//            dos.writeInt(viewCount);
//        } catch (IOException e) {
//            log.error("",e);
//        } catch (URISyntaxException e) {
//            log.error("",e);
//        }

        log.error("Listener End count : {}", viewCount);

    }

    private void registerUsers(UserRepository userRepository) {
        List<User> userList = new ArrayList<>();
        userList.add(new GeneralUser("merge", "1234", "sh"));
        userList.add(new GeneralUser("find", "1234", "abc"));
        userList.add(new GeneralUser("nhn", "1234", "nhn"));

        for (User user : userList) {
            userRepository.add(user);
        }

    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        return Optional.ofNullable(req.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }
}
