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
    private String need;
    @NotNull
    private String count;

    public PartViewModel(@NotNull String id, @NotNull @Min(3) String name, @NotNull String need, @NotNull String count) {
        System.out.println(id + " " + name + " " + need + " " + count);
        this.id = id;
        this.name = name;
        this.need = need;
        this.count = count;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String isRequired() {
        return this.need;
    }

    public String getCount() {
        return count;
    }
}
