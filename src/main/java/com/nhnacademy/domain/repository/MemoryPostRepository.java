package com.nhnacademy.domain.repository;

import com.nhnacademy.domain.post.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryPostRepository implements PostRepository{
    private Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private long idCnt = 0;

    @Override
    public long register(Post post) {
        ++idCnt;
        postMap.put(idCnt, post);
        return idCnt;
    }

    @Override
    public void modify(Post post) {
        postMap.put(post.getId(), post);
    }

    @Override
    public Post remove(long id) {
        return postMap.remove(postMap.get(id).getId());
    }

    @Override
    public Post getPost(long id) {
        return postMap.get(id);
    }

    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(postMap.values());
    }

}
