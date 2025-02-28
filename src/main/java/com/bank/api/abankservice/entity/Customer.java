package com.bank.api.abankservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_tbl")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String idSerialNumber;
    private String fin;
    private String address;
    private String phone;
    private Date dob;
    private String pin;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;
    @ColumnDefault(value = "1")
    private int active;

}
