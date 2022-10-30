package com.nhnacademy.controller.post;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.post.Post;
import com.nhnacademy.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class PostModifyController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        String writer_id = req.getParameter("writer_id");

        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Post post = postRepository.getPost(id);
        if (!Objects.isNull(post)
                && post.getId() == id
                && post.getWriterUserId().equals(writer_id)) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            boolean isChanged = false;
            if (!post.getTitle().equals(title) && !title.equals("")) {
                post.setTitle(title);
                isChanged = true;
            }
            if (!post.getContent().equals(content) && !content.equals("")) {
                post.setContent(content);
                isChanged = true;
            }
            if (isChanged) {
                post.setWriteTime(LocalDateTime.now());
            }

            return "/post/postList.jsp";
        }
        return "redirect:/post/postList.jsp";
    }
}
