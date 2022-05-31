package com.EmperorPenguin.SangmyungBank.member.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.MemberException;
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

    @ApiModelProperty(required = true)
    private String loginId;
    @ApiModelProperty(required = true)
    private String password1;
    @ApiModelProperty(required = true)
    private String password2;
    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String email;
    @ApiModelProperty(required = true)
    private int age;
    @ApiModelProperty(required = true)
    private String sex;
    @ApiModelProperty(required = true)
    private String phoneNumber;
    @ApiModelProperty(required = true)
    private String question;
    @ApiModelProperty(required = true)
    private String ansWord;

    public boolean checkNull(){
        if(loginId.length() == 0 |
                password1.length() == 0 |
                password2.length() == 0 |
                name.length() == 0 |
                email.length() == 0 |
                age == 0 |
                sex.length() == 0|
                phoneNumber.length() == 0 |
                question.length() == 0 |
                ansWord.length() == 0 ){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkLength(){
        if (sex.length() >= 2 | phoneNumber.length() >= 14){
            return true;
        }
        else{
            return false;
        }
    }

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
