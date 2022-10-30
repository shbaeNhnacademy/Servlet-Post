package com.nhnacademy.controller.post;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.post.ConcretePost;
import com.nhnacademy.domain.post.Post;
import com.nhnacademy.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PostRegisterController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer_id = req.getParameter("writer_id");

        Post post = new ConcretePost();
        post.setTitle(title);
        post.setContent(content);
        post.setWriterUserId(writer_id);

        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        post.setId(postRepository.register(post));

        return "redirect:/post/postList.jsp";
    }
}
