package com.in28minutes.rest.webservices.restfullwebservices.versioning;

public class PersonV1 {
    private String name;

    public PersonV1() {
    }

    public PersonV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("PersonV1 [name=%s]", this.name);
    }
}
