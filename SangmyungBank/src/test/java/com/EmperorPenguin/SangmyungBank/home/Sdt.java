package com.EmperorPenguin.SangmyungBank.home;


import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.EmperorPenguin.SangmyungBank.securityCard.service.SecurityCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class Sdt {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private SecurityCardService securityCardService;



    @Test
    void 무엇()throws NoSuchAlgorithmException {

        String hashedData="a5609eb0fef963f7da8ee95b51673f27";

        String Data ="eyJBdXRob3JpemF0aW9uIjoiSldUUmVmcmVzaFRva2VuIiwidHlwIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2NjQ5NzQyNDB9.9yB37UWAlsNbMErIXa3l9osg44H1dBWTjOw-YlhxogqAfM9PjOK5mojJYbRckJ-AVHycmGq14CeiDFLxF1bLow";
        int num1=33;
        int num2=33;
        String salt = String.format("%d%d",num1,num2);
        String hashData = securityCardService.hashingData(Data+salt);
        System.out.println(hashData.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(hashData);
        System.out.println(hashData.substring(0,32));
        System.out.println(hashData.substring(0,32).getBytes(StandardCharsets.UTF_8).length);
        if(!hashData.substring(0,32).equals(hashedData))
            throw new BaseException(ExceptionMessages.ERROR_SECURITYCARD_NOT_MATCH);
        else{
            System.out.println("성공");
        }
    }






    }

