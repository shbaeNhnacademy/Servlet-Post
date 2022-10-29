package com.nhnacademy.controller.user;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.post.ConcretePost;
import com.nhnacademy.domain.post.Post;
import com.nhnacademy.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PseudoColumnUsage;

@Slf4j
public class PostRegisterController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("{}", req.getMethod());

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        log.info("title : {}", title);

        Post post = new ConcretePost();
        post.setTitle(title);
        post.setContent(content);
        post.setWriterUserId("merge");

        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        postRepository.register(post);

        return "/postList.jsp";
    }
}
