package com.EmperorPenguin.SangmyungBank.api.securitynotices.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.securitynotices.domain.securitynotices.SecurityNotices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityNoticesRepository extends JpaRepository<SecurityNotices, Long> { }
