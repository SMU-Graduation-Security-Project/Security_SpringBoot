package com.EmperorPenguin.SangmyungBank.counsel.repository;

import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CounselRepository extends JpaRepository<Counsel, Long> {

    @Query("select c from Counsel c where c.memberId = ?1")
    List<Counsel> findAllByMemberId(@Param("memberId") Member memberId);

    @Modifying
    @Query("update Counsel c set c.title = ?2, c.content = ?3, c.modifyDate = ?4 where c.id = ?1")
    void updateCounsel(@Param("id")Long id, @Param("title") String title, @Param("content") String content, @Param("modifyDate") String modifyDate);
}
