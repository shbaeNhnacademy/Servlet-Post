package com.nhnacademy.listener;

import com.nhnacademy.domain.post.ConcretePost;
import com.nhnacademy.domain.post.Post;
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
        registerUsers(userRepository,postRepository);

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

    private void registerUsers(UserRepository userRepository, PostRepository postRepository) {

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
