package com.github.pranay.firstchar.model;

/**
 * Created by Pranay.
 */

public class PersonModel {
    private String personName;

    public PersonModel(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
