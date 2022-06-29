package com.EmperorPenguin.SangmyungBank.otp.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.service.exception.OtpException;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpCreateReq;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpRequestRes;
import com.EmperorPenguin.SangmyungBank.otp.entity.Otp;
import com.EmperorPenguin.SangmyungBank.otp.repository.OtpRepository;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @Transactional
    public void createOtp(OtpCreateReq otpCreateReq) {

        String loginId = otpCreateReq.getLoginId();


        try {
            otpRepository.save(otpCreateReq.toEntity(
                    memberService.getMember(loginId))
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new OtpException("Otp 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<OtpRequestRes> otpList(String loginId) {
        memberService.checkEmptyMember(loginId);

        return otpRepository
                .findAllByMemberId(memberService.getMember(loginId))
                .stream()
                .map(Otp::toDto)
                .collect(Collectors.toList());
    }

    }



