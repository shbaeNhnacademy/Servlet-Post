package com.nhnacademy.listener;

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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebListener
public class WebAppListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("locale", "en");

        Admin admin = new Admin("admin","12345","관리자");
        servletContext.setAttribute("admin", admin);

        UserRepository userRepository = new MemoryUserRepository();
        PostRepository postRepository = new MemoryPostRepository();

        JsonUserRepository jsonUserRepository = new JsonUserRepository();

        //TODO 테스트 사용자 등록
        registerUsersAndPosts(userRepository,postRepository);

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

    }

    private void registerUsersAndPosts(UserRepository userRepository, PostRepository postRepository) {

        List<User> userList = new ArrayList<>();
        GeneralUser generalUser = new GeneralUser("merge", "1234", "sh");
        generalUser.setProfileFileName("tutle.png");
        userList.add(generalUser);
        userList.add(new GeneralUser("find", "1234", "abc"));
        userList.add(new GeneralUser("nhn", "1234", "nhn"));

        for (User user : userList) {
            userRepository.add(user);
        }

        ConcretePost post1 = new ConcretePost();
        post1.setTitle("eqiuopgbwiodnfiopdab uofdbn iobs odbqou wsbuod gbquiopsbduiosvqyidfvb qosuvdb yisqvb uiv");
        post1.setContent("eqiuopgbwiodn124fiop quiopsbduiosvqyidfvb qosuv");
        post1.setWriterUserId(userList.get(0).getId());
        post1.setId(postRepository.register(post1));


        ConcretePost post2 = new ConcretePost();
        post2.setTitle("eqiuopgb wdqs  svqyidfvb qosuvdb yisqvb uiv ;lllllasxfmnoiadsbn fuoiqb uosuidbuwbd uiybuis bauo bsduivb asuyivb duyisvb duioasbnd uiowqev puod");
        post2.setContent("eqiuo quiopsbduiosvqyidfvb qosuv");
        post2.setWriterUserId(userList.get(1).getId());
        post2.setId(postRepository.register(post2));

    }


}
