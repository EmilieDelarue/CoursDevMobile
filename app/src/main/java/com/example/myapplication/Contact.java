package com.example.myapplication;

public class Contact {
    private int id;
    private String name;
    private String firstName;
    private String number;

    public Contact(String name, String firstName, String number) {
        this.name = name;
        this.firstName = firstName;
        this.number = number;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }



}
