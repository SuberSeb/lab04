package ru.user.lab04.servletapp.model;

import java.util.Date;

public class User extends Entity {
    private String name;
    private Date birthDate;
    private String email;
    private String city;
    private int group;

    public User(String name, Date birthDate, String email, String city, int group) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.city = city;
        this.group = group;
    }

    public User(int id, String name, Date birthDate, String email, String city, int group) {
        this.setId(id);
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.city = city;
        this.group = group;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return getId() +
                ". Имя: " + name +
                " | Дата рождения: " + birthDate +
                " | Почта: " + email +
                " | Город: " + city +
                " | Группа: " + group;
    }
}
