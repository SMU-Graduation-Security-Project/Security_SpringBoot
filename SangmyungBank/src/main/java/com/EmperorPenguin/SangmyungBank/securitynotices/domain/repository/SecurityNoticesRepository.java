package com.EmperorPenguin.SangmyungBank.securitynotices.domain.repository;

import com.EmperorPenguin.SangmyungBank.securitynotices.domain.securitynotices.SecurityNotices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityNoticesRepository extends JpaRepository<SecurityNotices, Long> { }
