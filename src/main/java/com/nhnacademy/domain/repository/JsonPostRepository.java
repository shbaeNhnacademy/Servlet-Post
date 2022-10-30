package com.nhnacademy.domain.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.domain.post.ConcretePost;
import com.nhnacademy.domain.post.Post;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonPostRepository implements PostRepository{
    private Map<Long, Post> postMap = new ConcurrentHashMap<>();
    private long idCnt = 0;

    private ServletContext servletContext;

    private final static String postJsonFileName = "posts.json";

    public JsonPostRepository(ServletContext servletContext) {
        this.servletContext = servletContext;
        generatePostsFromJson(postJsonFileName);
    }

    private void generatePostsFromJson(String jsonFileName) {

        String filePath = "/WEB-INF/classes/json/"+ jsonFileName;
        ObjectMapper mapper = new ObjectMapper();
        try(InputStream inputStream = Files.newInputStream(Paths.get(servletContext.getResource(filePath).toURI()));) {
            List<Map<String,String>> mapList = mapper.readValue(inputStream, List.class);
            for (Map<String, String> map : mapList) {
                long id = Long.parseLong(String.valueOf(map.get("id")));
                if (id > idCnt) {
                    idCnt = id - 1;
                }
                ConcretePost post = new ConcretePost();
                post.setTitle(map.get("title"));
                post.setContent(map.get("content"));
                post.setWriterUserId(map.get("writerUserId"));
                post.setWriteTime(LocalDateTime.parse(map.get("writeTime")));
                post.setViewCount(Integer.parseInt(String.valueOf(map.get("viewCount"))));
                post.setId(this.register(post));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void savePostsToJson(String jsonFileName) {
        String filePath = "/WEB-INF/classes/json/"+ jsonFileName;
        ObjectMapper mapper = new ObjectMapper();
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(servletContext.getResource(filePath).toURI()));) {
            List<Map<String, String>> mapList = new ArrayList<>();
            for (Post post : postMap.values()) {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(post.getId()));
                map.put("title", post.getTitle());
                map.put("content", post.getContent());
                map.put("writerUserId", post.getWriterUserId());
                map.put("writeTime", post.getWriteTime().toString());
                map.put("viewCount", String.valueOf(post.getViewCount()));
                mapList.add(map);
            }
            mapper.writeValue(outputStream, mapList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long register(Post post) {
        ++idCnt;
        postMap.put(idCnt, post);
        savePostsToJson(postJsonFileName);
        return idCnt;
    }

    @Override
    public void modify(Post post) {
        postMap.put(post.getId(), post);
        savePostsToJson(postJsonFileName);
    }

    @Override
    public Post remove(long id) {
        Post remove = postMap.remove(postMap.get(id).getId());
        savePostsToJson(postJsonFileName);
        return remove;
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
