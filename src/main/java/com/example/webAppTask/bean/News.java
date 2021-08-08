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

    public News(String title, String content, String whoWrote, Date date) {
        this.title = title;
        this.content = content;
        this.whoWrote = whoWrote;
        this.date = date;
    }

    // можно добавить к новости автора на выбор при добавлении
    // по дефолту будет анонимный

    // еще при выборе автора с пустыми полями будет просить -
    // перейти в личную страницу для заполнения профиля

    // и на отображении должно быть это значение
}