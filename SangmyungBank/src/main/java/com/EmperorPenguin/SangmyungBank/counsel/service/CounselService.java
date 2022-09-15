package com.EmperorPenguin.SangmyungBank.counsel.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselCreateReq;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselInquiryRes;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselUpdateReq;
import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import com.EmperorPenguin.SangmyungBank.counsel.repository.CounselRepository;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CounselService {

    private final CounselRepository counselRepository;
    private final MemberService memberService;

    @Transactional
    public void createCounsel(CounselCreateReq counselCreateReq) {
        String loginId = counselCreateReq.getLoginId();
        // 먼저 해당 사용자가 있는 지 검증
        memberService.checkEmptyMember(loginId);
        try {
            counselRepository.save(counselCreateReq.toEntity(
                    memberService.getMember(loginId))
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("상담글 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<CounselInquiryRes> listAllCounsel(String loginId) {
        Member member = memberService.getMember(loginId);

        return counselRepository.findAllByMemberId(member)
                .stream()
                .sorted(Comparator.comparing(Counsel::getId).reversed())
                .map(Counsel::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Counsel getSingleCounsel (Long id) {
        if(!counselRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_COUNSEL_NOT_EXIST);
        }
        return counselRepository
                .findById(id)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateCounsel(CounselUpdateReq counselUpdateReq) {
        if(!counselRepository.existsById(counselUpdateReq.getId())){
            throw new BaseException(ExceptionMessages.ERROR_EVENT_NOT_EXIST);
        }
        Counsel counsel = counselRepository.getById(counselUpdateReq.getId());
        if(!counsel.getMemberId().getLoginId().equals(counselUpdateReq.getLoginId())){
            throw new BaseException(ExceptionMessages.ERROR_COUNSEL_UNAUTHORIZED_ACCESS);
        }
        try {
            counselRepository.updateCounsel(counselUpdateReq.getId(),
                    counselUpdateReq.getTitle(),
                    counselUpdateReq.getContent(),
                    new DateConfig().getDateTime());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("상담글 업데이트에 실패했습니다.");
        }
    }

    @Transactional
    public void deleteCounsel(Long id, String loginId) {
        if(!counselRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_COUNSEL_NOT_EXIST);
        }
        Counsel counsel = counselRepository.getById(id);
        if(!counsel.getMemberId().getLoginId().equals(loginId)){
            throw new BaseException(ExceptionMessages.ERROR_COUNSEL_UNAUTHORIZED_ACCESS);
        }
        try{
            counselRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("상담글 삭제에 실패했습니다.");
        }
    }
}
