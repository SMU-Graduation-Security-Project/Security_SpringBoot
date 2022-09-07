package com.EmperorPenguin.SangmyungBank.baseUtil.config.auth;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.MemberException;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// localhost:8080/login이 동작을 안함. 기본적인 로그인.

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOGIN");
        Member memberEntity = memberRepository.findByLoginId(username)
                .orElseThrow(()-> new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));

        return new PrincipalDetails(memberEntity);
    }
}
