package com.EmperorPenguin.SangmyungBank.home;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberFindPasswordReq;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberPasswordUpdateReq;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberRegisterReq;

import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class MemberTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void 회원가입() {

        // given
        String loginId = "SpringTest1";
        String password1 = "11111111a!";
        String password2 = "11111111a!";
        String name = "Spring";
        String email = "SpringTest@email.com";
        String question = "테스트입니까?";
        String ansWord = "테스트";
        String sex = "M";
        int age = 13;
        String phoneNumber = "010-1111-1111";

        MemberRegisterReq memberRegisterReq = MemberRegisterReq.builder()
                .loginId(loginId)
                .password1(password1)
                .password2(password2)
                .name(name)
                .email(email)
                .age(age)
                .sex(sex)
                .question(question)
                .ansWord(ansWord)
                .phoneNumber(phoneNumber)
                .build();

        // when
        memberService.register(memberRegisterReq);
        Member findMember = memberRepository.findByLoginId(loginId).get();

        // then
        assertThat(findMember.getLoginId()).isEqualTo(loginId);
        passwordEncoder.matches(findMember.getPassword(),password1);
        assertThat(findMember.getName()).isEqualTo(name);
        assertThat(findMember.getEmail()).isEqualTo(email);
        assertThat(findMember.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(findMember.getSex()).isEqualTo(sex);
        assertThat(findMember.getQuestion()).isEqualTo(question);
        assertThat(findMember.getAnsWord()).isEqualTo(ansWord);

    }

    @Test
    void 사용자로그인() {
        MemberRegisterReq memberRegisterReq = MemberRegisterReq.builder()
                .loginId("SpringTest1")
                .password1("11111111a!")
                .password2("11111111a!")
                .name("테스트")
                .email("SpringTest@email.com")
                .age(13)
                .sex("M")
                .phoneNumber("010-1111-1111")
                .question("테스트입니까?")
                .ansWord("네 테스트입니다.")
                .build();
        memberService.register(memberRegisterReq);

        // given
        String loginId = "SpringTest1";
        String password ="11111111a!";
        MemberLoginReq memberLoginReq = MemberLoginReq.builder()
                .loginId(loginId)
                .password(password)
                .build();

        // when
        Member loginMember = memberService.login(memberLoginReq);
        Member dbMember = memberRepository.findByLoginId(loginId).get();

        // then
        assertThat(loginMember).isEqualTo(dbMember);

    }

    @Test
    void 사용자비밀번호찾기() {

        MemberRegisterReq memberRegisterReq = MemberRegisterReq.builder()
                .loginId("SpringTest1")
                .password1("11111111a!")
                .password2("11111111a!")
                .name("테스트")
                .email("SpringTest@email.com")
                .age(13)
                .sex("M")
                .phoneNumber("010-1111-1111")
                .question("테스트입니까?")
                .ansWord("네 테스트입니다.")
                .build();
        memberService.register(memberRegisterReq);

        // given
        String loginId = "SpringTest1";
        String question = "테스트입니까?";
        String ansWord = "네 테스트입니다.";
        MemberFindPasswordReq memberFindPasswordReq = MemberFindPasswordReq.builder()
                .loginId(loginId)
                .question(question)
                .ansWord(ansWord)
                .build();

        // when
        String templatePassword = memberService.setTemplatePassword(memberFindPasswordReq);
        Member dbMember = memberRepository.findByLoginId(loginId).get();

        // then
        passwordEncoder.matches(templatePassword, dbMember.getPassword());
    }

    @Test
    void 사용자비밀번호변경(){

        MemberRegisterReq memberRegisterReq = MemberRegisterReq.builder()
                .loginId("SpringTest1")
                .password1("11111111a!")
                .password2("11111111a!")
                .name("테스트")
                .email("SpringTest@email.com")
                .age(13)
                .sex("M")
                .phoneNumber("010-1111-1111")
                .question("테스트입니까?")
                .ansWord("네 테스트입니다.")
                .build();
        memberService.register(memberRegisterReq);

        String loginId = "SpringTest1";
        String question = "테스트입니까?";
        String ansWord = "네 테스트입니다.";
        MemberFindPasswordReq memberFindPasswordReq = MemberFindPasswordReq.builder()
                .loginId(loginId)
                .question(question)
                .ansWord(ansWord)
                .build();

        // given
        String oldPassword = memberService.setTemplatePassword(memberFindPasswordReq);
        String newPassword1 = "11111111a!";
        String newPassword2 = "11111111a!";

        MemberPasswordUpdateReq memberPasswordUpdateReq = MemberPasswordUpdateReq.builder()
                .loginId(loginId)
                .oldPassword(oldPassword)
                .newPassword1(newPassword1)
                .newPassword2(newPassword2)
                .build();

        // when
        memberService.updateNewPassword(memberPasswordUpdateReq);
        Member dbMember = memberRepository.findByLoginId(loginId).get();

        // then
        passwordEncoder.matches(newPassword1, dbMember.getPassword());
    }
}



