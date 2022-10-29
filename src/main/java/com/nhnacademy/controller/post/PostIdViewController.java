package com.nhnacademy.controller.post;

import com.nhnacademy.command.Command;
import com.nhnacademy.domain.post.Post;
import com.nhnacademy.domain.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostIdViewController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String id_do = split[split.length - 1];
        long postId = Long.parseLong(id_do.substring(0, id_do.length() - 3));

        //id로 post 리파지토리 찾아서 url로 던지기
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        Post post = postRepository.getPost(postId);
        post.increaseViewCount();
        req.setAttribute("post", post);

        //request에 담아서 화면에 뿌려주는 jsp로 보내야지
        return "/post/viewPost.jsp";

//        return "/post/postList.jsp";
    }
}
