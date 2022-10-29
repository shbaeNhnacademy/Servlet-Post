package com.nhnacademy.controller.post;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.post.Post;
import com.nhnacademy.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class PostDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        String writer_id = req.getParameter("writer_id");

        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Post post = postRepository.getPost(id);
        if (!Objects.isNull(post)
                && post.getId() == id
                && post.getWriterUserId().equals(writer_id)){
            Post remove = postRepository.remove(id);
            log.info("remove : {} / rest : {}", remove, postRepository.getPosts());
        }
        return "/post/postList.jsp";
    }

}
