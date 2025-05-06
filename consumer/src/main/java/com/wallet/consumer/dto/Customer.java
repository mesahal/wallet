package com.wallet.consumer.dto;


import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
}
