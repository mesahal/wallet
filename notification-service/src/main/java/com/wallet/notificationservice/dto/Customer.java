package com.wallet.notificationservice.dto;


import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;


    @Override
    public String toString() {
        return id + " " + name + " " + email + " " + phoneNumber;
    }
}
