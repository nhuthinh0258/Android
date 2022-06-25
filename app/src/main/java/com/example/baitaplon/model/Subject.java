package com.example.baitaplon.model;

public class Subject {
    //id công việc
    private int id;
    // tên công việc
    private String name;
    // Lương
    private int salary;
    // Phòng ban làm việc
    private String place;
    //Thời gian làm việc
    private String time;

    public Subject(String name, int salary, String place, String time) {
        this.name = name;
        this.salary = salary;
        this.place = place;
        this.time = time;
    }

    public Subject(int id, String name, int salary, String place, String time) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.place = place;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
