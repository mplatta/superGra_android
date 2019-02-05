package com.example.luba.supergraandroid;

public class Stat {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Stat(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
