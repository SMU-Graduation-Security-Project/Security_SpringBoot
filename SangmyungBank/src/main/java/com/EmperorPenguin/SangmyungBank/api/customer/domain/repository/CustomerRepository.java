package com.EmperorPenguin.SangmyungBank.api.customer.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.customer.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
