package com.EmperorPenguin.SangmyungBank.api.customer.service;

import com.EmperorPenguin.SangmyungBank.api.customer.domain.customer.Customer;
import com.EmperorPenguin.SangmyungBank.api.customer.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(@RequestBody Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> listAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty())
            return null;

        return customerList;
    }

    public Customer getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        return customer;
    }

    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);
        if (customer != null) {
            customer.setTitle(customerDetails.getTitle());
            customer.setContent(customerDetails.getContent());

            Customer updateCustomer = customerRepository.save(customer);
            return updateCustomer;
        }
        else {
            return null;
        }
    }

    public Customer deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            return  customer;
        }
        else {
            return null;
        }
    }
}
