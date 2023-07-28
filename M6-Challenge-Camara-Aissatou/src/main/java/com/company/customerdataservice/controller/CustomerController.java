package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    /** Create a new customer record */
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    /** Update an existing customer record */
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    /** Delete an existing customer record */
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    /** Find a customer record by id */
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        Optional<Customer> returnVal = customerRepository.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        else{
            return null;
        }
    }

    /** Find customer records by state */
    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomerByState(@PathVariable String state) {
        return customerRepository.findByState(state);
    }
}
