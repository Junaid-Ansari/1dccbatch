package com.example.domain;

public class MyData {
    private Long id;
    private String name;

    public MyData() {
    }

    public MyData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
