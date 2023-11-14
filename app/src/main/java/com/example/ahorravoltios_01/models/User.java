package com.example.ahorravoltios_01.models;

public class User {

    private final String idUser;
    private String name,email,phone,password;

    public User(String idUser,String name, String email, String phone, String password) {
        this.idUser=idUser;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;

    }

    public String getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
