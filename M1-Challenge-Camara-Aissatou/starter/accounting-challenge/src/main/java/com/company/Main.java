package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static List<Customer> helper (List<String[]> customerInfo){
        List<Customer> finalList = new ArrayList<>(); // a list of customers
        AccountRecord record;
        Customer customer;
        boolean found; // keep tracker of whether the customer is already in the list of customers
        for (String[] arr : customerInfo){ // loop through each string array
            found = false;
            // loop through the finalList
            if(!finalList.isEmpty()){
                for (Customer custmr : finalList) {
                    if (custmr.getId() == (Integer.parseInt(arr[0]))) { // if the customer already exist
                        record = new AccountRecord(); // new record is still needed so that it can be appended to customer's charge list
                        record.setCharge(Integer.parseInt(arr[2]));
                        record.setChargeDate(arr[3]);
                        custmr.getCharges().add(record);
                        found = true;
                        break; // don't need to continue looping if we already found the cutomer in the list
                    }
                }
            }
            if(found == false || finalList.isEmpty()){ // when the customer list is empty or we've gone through it and there's no customer with the given id
                customer = new Customer();

                // parse through the string array of each customer
                customer.setId(Integer.parseInt(arr[0]));
                customer.setName(arr[1]);
                record = new AccountRecord();
                record.setCharge(Integer.parseInt(arr[2])); // convert charge to int
                record.setChargeDate(arr[3]);
                customer.getCharges().add(record); // append charge to customer's list of charges
                finalList.add(customer); // add the customer to the list of customers
            }
        }
        return finalList; // return a list of distinct customers
    }

    public static void main(String[] args) {
        List<Customer> finalList = helper (customerData);

        //Update this
        System.out.println("Positive accounts:");
        for (Customer customer : finalList) {
            if(customer.getBalance() >= 0){ // positive balances
                System.out.println(customer.toString());
            }
        }
        System.out.println("Negative accounts:");
        for (Customer customer : finalList) {
            if(customer.getBalance() < 0){ // negative balances
                System.out.println(customer.toString());
            }
        }
    }
}
