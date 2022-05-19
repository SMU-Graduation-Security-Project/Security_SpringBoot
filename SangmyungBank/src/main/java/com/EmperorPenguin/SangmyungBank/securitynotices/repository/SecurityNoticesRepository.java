package com.EmperorPenguin.SangmyungBank.securitynotices.repository;

import com.EmperorPenguin.SangmyungBank.securitynotices.entity.SecurityNotices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SecurityNoticesRepository extends JpaRepository<SecurityNotices, Long> {

    Optional<SecurityNotices> findByTitle(String title);

    @Modifying
    @Query("update SecurityNotices s set s.title = ?2, s.content = ?3 where s.id = ?1")
    void updateSecurityNotice(@Param("id")Long id, @Param("title") String title, @Param("content") String content);
}
