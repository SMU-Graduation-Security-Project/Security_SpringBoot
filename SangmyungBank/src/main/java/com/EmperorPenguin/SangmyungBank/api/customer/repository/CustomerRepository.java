package com.EmperorPenguin.SangmyungBank.api.customer.repository;

import com.EmperorPenguin.SangmyungBank.api.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
