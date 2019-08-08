package com.nomad.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.*;

public class User {

    private final Long id;

    @NotBlank(message = "username.notblank")
    @Size(min = 5, max = 16, message = "username.size")
    private String username;

    @NotBlank(message = "sex.notblank")
    @Size(min = 1, max = 1, message = "sex.size")
    private String sex;

    @Min(value = 10, message = "age.min")
    @Max(value = 70, message = "age.max")
    private int age;


    public User() {
        this.id = null;
    }

    public User(String username, String sex, int age) {
        this.id = null;
        this.username = username;
        this.sex = sex;
        this.age = age;
    }

    public User(long id, String username, String sex, int age) {
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

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(String sex) {
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
