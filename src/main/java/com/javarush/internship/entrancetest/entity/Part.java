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
    private boolean need;

    @Column(name = "count")
    private Integer count;

    public Part() {
        this.id = idCounter;
        idCounter++;
    }

    public Part(String name, boolean need, int count) {
        this();
        this.name = name;
        this.need = need;
        this.count = count;
    }

    /*
        public Part(int id, String name, boolean need, int count) {
            this.id = id;
            this.name = name;
            this.need = need;
            this.count = count;
        }
    */
    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public boolean isNeed() {
        return need;
    }

    public int getCount() {
        return count;
    }
}
