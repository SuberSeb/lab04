package ru.user.lab04.servletapp.model;

public class Group extends Entity {
    private String name;
    private String description;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group(int id, String name, String description) {
        this.setId(id);
        this.name = name;
        this.description = description;
    }

    public Group () {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getId() + ". Название: " + name + " | Описание: " + description;
    }
}
