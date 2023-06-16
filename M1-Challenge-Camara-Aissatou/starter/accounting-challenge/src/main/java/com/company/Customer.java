package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>(); // each customer has a list of charges attached to their acc

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // sum all charges for each customer account by going through their charge list
    public int getBalance() {
        //update this
        int sum = 0;
        for(AccountRecord record : charges){
            sum = sum + (record.getCharge());
        }
        return sum;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    // print the customer's id, name, and the sum of their charges
    @Override
    public String toString() {
        //update this
        return "Customer ID: " + this.getId() + ", Name: " + this.getName() + ", Balance: " + this.getBalance();
    }
}
