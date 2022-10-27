package com.nhnacademy.domain.post;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConcretePost implements Post{
    private long id = 0;
    private String title;
    private String content;
    private String writerUserId;
    private LocalDateTime writeTime;

    public ConcretePost() {
        this.id++;
        writeTime = LocalDateTime.now();
    }

    private int viewCount;


    @Override
    public void increaseViewCount() {
        this.viewCount++;
    }

    @Override
    public String toString() {
        return "ConcretePost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writerUserId='" + writerUserId + '\'' +
                ", writeTime=" + writeTime +
                ", viewCount=" + viewCount +
                '}';
    }
}
