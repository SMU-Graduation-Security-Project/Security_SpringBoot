package com.EmperorPenguin.SangmyungBank.member.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.member.entity.Role;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberRegisterReq {

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;
    @ApiModelProperty(required = true, value = "비밀번호",example = "Test1234!",position = 1)
    private String password1;
    @ApiModelProperty(required = true, value = "비밀번호",example = "Test1234!",position = 2)
    private String password2;
    @ApiModelProperty(required = true, value = "이름",example = "홍길동",position = 3)
    private String name;
    @ApiModelProperty(required = true, value = "이메일",example = "test1234@gmail.com",position = 4)
    private String email;
    @ApiModelProperty(required = true, value = "나이",example = "14",position = 5)
    private int age;
    @ApiModelProperty(required = true, value = "성별",example = "F",position = 6)
    private String sex;
    @ApiModelProperty(required = true, value = "전화번호",example = "010-0000-0000",position = 7)
    private String phoneNumber;
    @ApiModelProperty(required = true, value = "질문",example = "존경하는 교수님은?",position = 8)
    private String question;
    @ApiModelProperty(required = true, value = "답",example = "박종환교수님",position = 9)
    private String ansWord;


    public Member toEntity(String encryptedPassword, Role defaultRole)
    {
        return Member.builder()
                .loginId(loginId)
                .password(encryptedPassword)
                .email(email)
                .name(name)
                .age(age)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .registerDate(new DateConfig().getDateTime())
                .modifyDate(new DateConfig().getDateTime())
                .question(question)
                .ansWord(ansWord)
                .usingTempPassword(false)
                .role(defaultRole)
                .build();
    }


}
