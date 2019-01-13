package com.javarush.internship.entrancetest.api.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PartViewModel {
    private String id;

    @NotNull
    @Min(3)
    private String name;

    @NotNull
    private String required;

    @NotNull
    private String count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
