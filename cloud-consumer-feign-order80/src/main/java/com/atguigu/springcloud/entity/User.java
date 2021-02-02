package com.atguigu.springcloud.entity;

import java.util.Date;

public class User {


    private Long id;

    private String name;

    private Integer age;

    private Date create_dtme;

    private Date last_updtme;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreate_dtme() {
        return create_dtme;
    }

    public void setCreate_dtme(Date create_dtme) {
        this.create_dtme = create_dtme;
    }

    public Date getLast_updtme() {
        return last_updtme;
    }

    public void setLast_updtme(Date last_updtme) {
        this.last_updtme = last_updtme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", create_dtme=" + create_dtme +
                ", last_updtme=" + last_updtme +
                '}';
    }
}
