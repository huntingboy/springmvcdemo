package com.nomad.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class User {

    private final Long id;
    private final String username;
    private char sex;
    private int age;

    public User() {
        this.id = null;
        this.username = null;
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

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, "id");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }
}
