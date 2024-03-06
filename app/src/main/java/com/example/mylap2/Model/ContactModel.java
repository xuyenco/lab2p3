package com.example.mylap2.Model;

public class ContactModel {
    private int id;
    private String name;
    private String number;

    private String Image;

    public ContactModel(int id, String name, String number, String image) {
        this.id = id;
        this.name = name;
        this.number = number;
        Image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getImage() {
        return Image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setImage(String image) {
        Image = image;
    }
}
