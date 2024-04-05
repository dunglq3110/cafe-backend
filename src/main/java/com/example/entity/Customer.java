package com.example.entity;


import com.example.util.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.LinkedList;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double totalSpend;
    private Gender gender;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private LinkedList<Receipt> receipts = new LinkedList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LinkedList<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(LinkedList<Receipt> receipts) {
        this.receipts = receipts;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
