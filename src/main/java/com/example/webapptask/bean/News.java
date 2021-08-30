package com.example.webapptask.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class News implements Serializable {

    private int newsId;
    private String author;
    private String title;
    private String content;
    private Date date;

    public News(int newsId, String author, String title, String content, Date date) {
        this.newsId = newsId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}