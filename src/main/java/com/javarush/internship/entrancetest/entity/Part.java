package com.javarush.internship.entrancetest.entity;

import javax.persistence.*;

@Entity
@Table(name = "part", schema = "test", catalog = "")
public class Part{
    private static int idCounter;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "required")
    private boolean required;

    @Column(name = "count")
    private Integer count;

    public Part() {
        this.id = idCounter;
        idCounter++;
    }

    public Part(String name, boolean required, int count) {
        this();
        this.name = name;
        this.required = required;
        this.count = count;
    }

    public Part(int id, String name, boolean required, int count) {
        this.id = id;
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

    public boolean isRequired() {
        return required;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }
}
