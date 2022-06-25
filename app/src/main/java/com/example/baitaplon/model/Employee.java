package com.example.baitaplon.model;

public class Employee {

    private int id_employee;
    private String name_employee;
    private String sex;
    private String employee_code;
    private String Date_of_birth;
    private int id_subject;

    public Employee(String name_employee, String sex, String employee_code, String date_of_birth) {
        this.name_employee = name_employee;
        this.sex = sex;
        this.employee_code = employee_code;
        this.Date_of_birth = date_of_birth;
    }

    public Employee(String name_employee, String sex, String employee_code, String date_of_birth, int id_subject) {
        this.name_employee = name_employee;
        this.sex = sex;
        this.employee_code = employee_code;
        this.Date_of_birth = date_of_birth;
        this.id_subject = id_subject;
    }

    public Employee(int id_employee, String name_employee, String sex, String employee_code, String date_of_birth, int id_subject) {
        this.id_employee = id_employee;
        this.name_employee = name_employee;
        this.sex = sex;
        this.employee_code = employee_code;
        this.Date_of_birth = date_of_birth;
        this.id_subject = id_subject;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getName_employee() {
        return name_employee;
    }

    public void setName_employee(String name_employee) {
        this.name_employee = name_employee;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getDate_of_birth() {
        return Date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        Date_of_birth = date_of_birth;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }
}
