package com.javarush.internship.entrancetest.entity;

import javax.persistence.*;

@Entity
@Table(name = "part", schema = "test", catalog = "")
public class Part{
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "required")
    private boolean required;
    @Column(name = "count")
    private int count;

    public Part() {
    }

    public Part(String name, boolean required, int count) {
        this.name = name;
        this.required = required;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
