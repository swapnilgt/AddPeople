package com.example.swapnilgupta.addpeople.models;

/**
 * Created by swapnilgupta on 30/08/2017.
 */

public class Person {

    private int id;
    private int name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
