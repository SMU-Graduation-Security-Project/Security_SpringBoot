package com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt;

import java.util.List;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.auth.PrincipalDetails;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
// @CrossOrigin  // CORS 허용
public class RestApiController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 모든 사람이 접근 가능
    @GetMapping("/home")
    public String home() {
        return "<h1>home</h1>";
    }

    // Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용 불가능.
    // 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.

    // 유저 혹은 매니저 혹은 어드민이 접근 가능
    @GetMapping("/user")

        public String user(Authentication authentication) {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("principal : "+principal.getMember().getMemberId());
            System.out.println("principal : "+principal.getMember().getLoginId());
            System.out.println("principal : "+principal.getMember().getPassword());

            return "<h1>user</h1>";
    }

    // 매니저 혹은 어드민이 접근 가능
    @GetMapping("manager")
    public String manager(@RequestBody Member member) {


        return "manager";
    }

    // 어드민이 접근 가능
    @GetMapping("/admin")
    public String admin(@RequestBody Member member) {


        return "admin";
    }
    @PostMapping("/join")
    public String join(@RequestBody Member member) {
        member.getPassword();
        member.getRole();
        memberRepository.save(member);
        return "회원가입완료";
    }

}