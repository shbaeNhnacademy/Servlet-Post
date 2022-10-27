package com.nhnacademy.domain.post;

import java.time.LocalDateTime;

public interface Post {
    long getId(); // 게시물을 등록하면 id 값을 반환
    void setId(long id);

    String getTitle();
    void setTitle(String title);

    String getContent();
    void setContent(String content);

    String getWriterUserId();
    void setWriterUserId(String writerUserId);

    LocalDateTime getWriteTime();
    void setWriteTime(LocalDateTime writeTime);

    int getViewCount();
    void increaseViewCount();
}

