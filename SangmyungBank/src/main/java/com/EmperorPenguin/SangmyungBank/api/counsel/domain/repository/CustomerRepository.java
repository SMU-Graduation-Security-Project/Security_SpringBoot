package com.EmperorPenguin.SangmyungBank.api.counsel.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.counsel.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
