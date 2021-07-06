package com.example.webAppTask.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class News implements Serializable {
    private String title;
    private String brief;

    public News() {

    }

    public News(String title, String brief) {
        this.title = title;
        this.brief = brief;
    }
}
