package com.nhnacademy.domain.post;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ConcretePost implements Post{
    private long id;
    private String title;
    private String content;
    private String writerUserId;
    private LocalDateTime writeTime;
    private int viewCount;
    public ConcretePost() {
        writeTime = LocalDateTime.now();
    }

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
