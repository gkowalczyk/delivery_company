package com.example.infrastructures.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Entity(name = "Customers")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender_address;
    private String recipient_address;
    private String package_type;
    private String package_size;
    public LocalDate delivery_date;

    public Customer(String sender_address, String recipient_address, String package_type, String package_size, LocalDate delivery_date) {
        this.sender_address = sender_address;
        this.recipient_address = recipient_address;
        this.package_type = package_type;
        this.package_size = package_size;
        this.delivery_date = delivery_date;
    }
}


