package com.EmperorPenguin.SangmyungBank.api.securitynotices.repository;

import com.EmperorPenguin.SangmyungBank.api.securitynotices.model.SecurityNotices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityNoticesRepository extends JpaRepository<SecurityNotices, Long> { }
