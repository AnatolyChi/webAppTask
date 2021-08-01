package com.example.webAppTask.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class News implements Serializable {
    private String title;
    private String content;
    private String whoWrote;
    private Date date;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
