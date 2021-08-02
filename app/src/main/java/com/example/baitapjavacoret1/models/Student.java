package com.example.baitapjavacoret1.models;

public class Student {
    private String name;
    private int yearOfBirth;
    private String phoneNumber;
    private String specialized;

    public Student() {
    }

    public Student(String name, int yearOfBirth, String phoneNumber, String specialized) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialized = specialized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }
}
