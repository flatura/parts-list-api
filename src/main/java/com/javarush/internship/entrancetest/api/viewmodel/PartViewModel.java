package com.javarush.internship.entrancetest.api.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PartViewModel {

    @NotNull
    private String id;
    @NotNull
    @Min(3)
    private String name;
    @NotNull
    private String required;
    @NotNull
    private String count;

    public int getId() {
        return Integer.parseInt(id);
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRequired() {
        return required.equals("true");
    }

    public void setRequired(boolean required) {
        this.required = required ? "true" : "false";
    }

    public int getCount() {
        return Integer.parseInt(count);
    }

    public void setCount(int count) {
        this.count = String.valueOf(count);
    }
}
