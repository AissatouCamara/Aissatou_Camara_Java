package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer;
    List<AccountRecord> accountRecordList;

    @BeforeEach
    public void setUp(){
        // at the start of each method a new customer and customer charge list is created
        accountRecordList = new ArrayList<>();
        customer = new Customer();
        customer.setName("Aissatou Camara");
        customer.setId(1);
    }

    // if the account record list is empty
    @Test
    void getBalanceWithEmptyAccRecordList() {
        assertEquals(0, customer.getBalance());
    }

    // test get balance method with only pos nums
    @Test
    void getBalanceWithPositiveNumbers() {
        accountRecordList.add(new AccountRecord()); // add a new account record to the customer charge list
        accountRecordList.get(0).setCharge(500); //set the charge. get(0) -> first account record from the list

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(1).setCharge(300);

        customer.getCharges().add(accountRecordList.get(0)); // append the charge to the customer's charges
        customer.getCharges().add(accountRecordList.get(1));

        assertEquals(800, customer.getBalance());
    }

    // test get balance method with only neg nums
    @Test
    void getBalanceWithNegativeNumbers() {
        accountRecordList.add(new AccountRecord());
        accountRecordList.get(0).setCharge(-500);

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(1).setCharge(-300);

        customer.getCharges().add(accountRecordList.get(0));
        customer.getCharges().add(accountRecordList.get(1));

        assertEquals(-800, customer.getBalance());
    }

    // test get balance method with both pos and neg nums
    @Test
    void getBalanceWithPosAndNegNumbers() {
        accountRecordList.add(new AccountRecord());
        accountRecordList.get(0).setCharge(500);

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(1).setCharge(-300);

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(2).setCharge(100);

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(3).setCharge(-900);


        customer.getCharges().add(accountRecordList.get(0));
        customer.getCharges().add(accountRecordList.get(1));
        customer.getCharges().add(accountRecordList.get(2));
        customer.getCharges().add(accountRecordList.get(3));

        assertEquals(-600, customer.getBalance());
    }

    // test toString method
    @Test
    void testToString() {
        accountRecordList.add(new AccountRecord());
        accountRecordList.get(0).setCharge(500);

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(1).setCharge(-300);

        accountRecordList.add(new AccountRecord());
        accountRecordList.get(2).setCharge(100);

        customer.getCharges().add(accountRecordList.get(0));
        customer.getCharges().add(accountRecordList.get(1));
        customer.getCharges().add(accountRecordList.get(2));

        assertEquals("Customer ID: " + 1 + ", Name: Aissatou Camara"  + ", Balance: " + 300, customer.toString());
    }
}