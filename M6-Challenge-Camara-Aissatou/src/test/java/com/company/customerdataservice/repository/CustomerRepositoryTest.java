package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception{
        customerRepository.deleteAll();
        customer = new Customer();
        customer.setFirstName("Emily");
        customer.setLastName("Rose");
        customer.setEmail("abcdef@gmail.com");
        customer.setCompany("Netflix");
        customer.setPhone("6029237469");
        customer.setAddress1("6023 N Sunset Ave");
        customer.setAddress2("Apt 403");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");
    }

    @Test
    public void addCustomer() {

        /**
         * Create a new customer record
         */
        /**Act*/
        customerRepository.save(customer);
        /**assert*/
        Optional<Customer> newCustomer = customerRepository.findById(customer.getId());
        assertEquals(newCustomer.get(), customer);
    }

    @Test
    public void updateCustomer() {
        customerRepository.save(customer);
        /**Act*/
        customer.setFirstName("Bailey");
        customerRepository.save(customer);
        /**assert*/
        Optional<Customer> newCustomer = customerRepository.findById(customer.getId());
        assertEquals(newCustomer.get(), customer);
    }

    @Test
    public void deleteCustomer() {
        customerRepository.save(customer);
        /**Act*/
        customerRepository.deleteById(customer.getId());
        /**assert*/
        Optional<Customer> newCustomer = customerRepository.findById(customer.getId());
        assertFalse(newCustomer.isPresent());
    }

    @Test
    public void getCustomerById() {
        customerRepository.save(customer);
        /**Act*/
        Customer newCustomer = customerRepository.findById(customer.getId()).orElse(null);

        /**assert*/
        assertEquals(customer, newCustomer);
    }


    @Test
    public void getCustomerByState() {

        customerRepository.save(customer);

        Customer customer1 = new Customer();
        customer1.setFirstName("Chelsea");
        customer1.setLastName("Rodriguez");
        customer1.setEmail("ghijk@gmail.com");
        customer1.setCompany("Google");
        customer1.setPhone("6039237569");
        customer1.setAddress1("6025 N Sunset Ave");
        customer1.setAddress2("Apt 404");
        customer1.setCity("Alhambra");
        customer1.setState("California");
        customer1.setPostalCode("98765");
        customer1.setCountry("United States");

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("James");
        customer2.setLastName("William");
        customer2.setEmail("lmnopq@gmail.com");
        customer2.setCompany("Meta");
        customer2.setPhone("9739437362");
        customer2.setAddress1("1234 S Murtle Ave");
        customer2.setAddress2("Apt 909");
        customer2.setCity("New York City");
        customer2.setState("New York");
        customer2.setPostalCode("00723");
        customer2.setCountry("United States");

        customerRepository.save(customer2);
        /**Act*/
        List<Customer> customersByState = customerRepository.findByState("California");

        /**assert*/
        assertTrue(customersByState.contains(customer));
        assertTrue(customersByState.contains(customer1));
        assertFalse(customersByState.contains(customer2));
    }

}