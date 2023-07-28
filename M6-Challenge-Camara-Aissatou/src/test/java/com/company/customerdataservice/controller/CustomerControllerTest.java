package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CustomerRepository customerRepository;
    private Customer customer;

    @BeforeEach
    public void setup() {
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
    public void addCustomerTest() throws Exception {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customers")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Emily"))
                .andExpect(jsonPath("$.lastName").value("Rose"))
                .andExpect(jsonPath("$.email").value("abcdef@gmail.com"))
                .andExpect(jsonPath("$.company").value("Netflix"))
                .andExpect(jsonPath("$.phone").value("6029237469"))
                .andExpect(jsonPath("$.address1").value("6023 N Sunset Ave"))
                .andExpect(jsonPath("$.address2").value("Apt 403"))
                .andExpect(jsonPath("$.city").value("Los Angeles"))
                .andExpect(jsonPath("$.state").value("California"))
                .andExpect(jsonPath("$.postalCode").value("12345"))
                .andExpect(jsonPath("$.country").value("United States"));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customers")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customers/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Emily"))
                .andExpect(jsonPath("$.lastName").value("Rose"))
                .andExpect(jsonPath("$.email").value("abcdef@gmail.com"))
                .andExpect(jsonPath("$.company").value("Netflix"))
                .andExpect(jsonPath("$.phone").value("6029237469"))
                .andExpect(jsonPath("$.address1").value("6023 N Sunset Ave"))
                .andExpect(jsonPath("$.address2").value("Apt 403"))
                .andExpect(jsonPath("$.city").value("Los Angeles"))
                .andExpect(jsonPath("$.state").value("California"))
                .andExpect(jsonPath("$.postalCode").value("12345"))
                .andExpect(jsonPath("$.country").value("United States"));
    }

    @Test
    public void getCustomerByStateTest() throws Exception {
        when(customerRepository.findByState("California")).thenReturn(Arrays.asList(customer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers/state/{state}", "California"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Emily"))
                .andExpect(jsonPath("$[0].lastName").value("Rose"))
                .andExpect(jsonPath("$[0].email").value("abcdef@gmail.com"))
                .andExpect(jsonPath("$[0].company").value("Netflix"))
                .andExpect(jsonPath("$[0].phone").value("6029237469"))
                .andExpect(jsonPath("$[0].address1").value("6023 N Sunset Ave"))
                .andExpect(jsonPath("$[0].address2").value("Apt 403"))
                .andExpect(jsonPath("$[0].city").value("Los Angeles"))
                .andExpect(jsonPath("$[0].state").value("California"))
                .andExpect(jsonPath("$[0].postalCode").value("12345"))
                .andExpect(jsonPath("$[0].country").value("United States"));
    }
}