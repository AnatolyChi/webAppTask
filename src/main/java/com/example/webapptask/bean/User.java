package com.example.webapptask.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class User implements Serializable {
    private String login;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String email;
    @Setter
    private String age;
    private String role;
    private Date dateRegistered;

    public User(String login, Date dateRegistered) {
        this.login = login;
        this.dateRegistered = dateRegistered;
    }

    public User(String login, String role, Date dateRegistered) {
        this.login = login;
        this.role = role;
        this.dateRegistered = dateRegistered;
    }

    // Почему в Edit Личной страницы ошибка при изменении локали
    // Та же проблема и в открытии новости
    // Изменить логику смены локали без или с referer

    // Connection Pool сделать интерфейсом(хз как лучше)
    // Выкачать видео Spring from iCloud
    // Досмотреть видео по Spring GeekBrains

    // Сделать новостям педжинацию до 10 новостей на странице
    // + Неавторизованный пользователь может просматривать только саммари новости (brief).
    // + Авторизованные пользователи могут просматривать список новостей и новости целиком.
    // + Авторизованный пользователь имеет профиль, профиль можно редактировать.
    // Администратор системы может добавлять новости, удалять, редактировать.
    // Авторизованный пользователь может добавить новость в Понравившееся и просматривать их отдельным списком.

    //Возможно реализация комментирования новости, возможна функциональность, когда авторизованный пользователь предлагает свою новость на публикацию, администратор ее рассматривает и утверждает или нет.
}
