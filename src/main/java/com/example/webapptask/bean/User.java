package com.example.webapptask.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class User implements Serializable {

    private int id;
    private String login;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String email;
    @Setter
    private int age;
    private String role;
    private Date dateRegistered;

    public static class Builder {
        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder id(int id) {
            newUser.id = id;
            return this;
        }

        public Builder login(String login) {
            newUser.login = login;
            return this;
        }

        public Builder firstname(String firstname) {
            newUser.firstName = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            newUser.lastName = lastname;
            return this;
        }

        public Builder email(String email) {
            newUser.email = email;
            return this;
        }

        public Builder age(int age) {
            newUser.age = age;
            return this;
        }

        public Builder role(String role) {
            newUser.role = role;
            return this;
        }

        public Builder dateRegistered(Date dateRegistered) {
            newUser.dateRegistered = dateRegistered;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
