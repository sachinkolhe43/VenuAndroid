package com.example.venuebooking.entity;

import java.io.Serializable;

public class User implements Serializable {

    private int User_id;

    private String User_name;

    private String User_email;

    private String User_pass;

    private String User_contact;

    private String User_address;

    public User() {
    }


    public User(int user_id, String user_name, String user_email, String user_pass, String user_contact, String user_address) {
        User_id = user_id;
        User_name = user_name;
        User_email = user_email;
        User_pass = user_pass;
        User_contact = user_contact;
        User_address = user_address;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getUser_email() {
        return User_email;
    }

    public void setUser_email(String user_email) {
        User_email = user_email;
    }

    public String getUser_pass() {
        return User_pass;
    }

    public void setUser_pass(String user_pass) {
        User_pass = user_pass;
    }

    public String getUser_contact() {
        return User_contact;
    }

    public void setUser_contact(String user_contact) {
        User_contact = user_contact;
    }

    public String getUser_address() {
        return User_address;
    }

    public void setUser_address(String user_address) {
        User_address = user_address;
    }


    @Override
    public String toString() {
        return "User{" +
                "User_id=" + User_id +
                ", User_name='" + User_name + '\'' +
                ", User_email='" + User_email + '\'' +
                ", User_pass='" + User_pass + '\'' +
                ", User_contact='" + User_contact + '\'' +
                ", User_address='" + User_address + '\'' +
                '}';
    }
}