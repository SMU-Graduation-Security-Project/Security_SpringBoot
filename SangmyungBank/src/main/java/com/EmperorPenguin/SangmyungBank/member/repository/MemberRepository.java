package com.EmperorPenguin.SangmyungBank.member.repository;

import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(Long userId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByEmail(String Email);

    Optional<Member> findByPhoneNumber(String PhoneNumber);

    Optional<Member> findByRefreshToken(String refreshToken);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.loginDate = :loginTime where m.loginId =:loginId")
    void updateLoginDate(@Param("loginTime")String loginTime, @Param("loginId")String LoginId);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.password = ?1, m.usingTempPassword = false where m.memberId = ?2")
    void updateUserPassword(@Param("newPassword")String newPassword, @Param("userId")Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.password = ?1, m.usingTempPassword = true where m.memberId = ?2")
    void updateUserTemplatePassword(@Param("templatePassword")String templatePassword, @Param("userId")Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.modifyDate = ?1 where m.memberId = ?2")
    void updateUserModifyDate(@Param("modifyDate")String modifyDate, @Param("userid")Long memberId);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}