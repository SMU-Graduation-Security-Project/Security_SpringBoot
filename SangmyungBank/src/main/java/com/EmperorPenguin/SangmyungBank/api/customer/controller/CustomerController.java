package com.EmperorPenguin.SangmyungBank.api.customer.controller;

import com.EmperorPenguin.SangmyungBank.api.customer.model.Customer;
import com.EmperorPenguin.SangmyungBank.api.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody Customer customer) {
        customer.setCreatedDate(LocalDateTime.now());
        Customer resultCustomer = customerService.createCustomer(customer);
        if(resultCustomer == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customerList = customerService.listAllCustomers();
        if (customerList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(customerList);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(customer);
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerService.updateCustomer(id, customerDetails);
        if (customer == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.deleteCustomer(id);
        if (customer == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
}
