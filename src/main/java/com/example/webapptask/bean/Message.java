package com.example.webapptask.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class Message implements Serializable {

    private int id;
    private String title;
    private String content;
    private Date date;

    public Message(int id, String content, Date date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public Message(int id, String title, String content, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
