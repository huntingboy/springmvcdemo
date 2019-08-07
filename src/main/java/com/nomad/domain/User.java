package com.nomad.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    private final Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "username.size")
    private String username;

    @NotNull
    @Size(min = 1, max = 1, message = "sex.size")
    private char sex;

    @NotNull
    @Size(min = 10,max = 70, message = "age.valid")
    private int age;


    public User() {
        this.id = null;
    }

    public User(String username, char sex, int age) {
        this.id = null;
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    public User(long id, String username, char sex, int age) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public char getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, "id");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }
}
