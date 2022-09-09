package com.EmperorPenguin.SangmyungBank.securitynotices.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeCreateReq;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeInquiryRes;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeUpdateReq;
import com.EmperorPenguin.SangmyungBank.securitynotices.entity.SecurityNotices;
import com.EmperorPenguin.SangmyungBank.securitynotices.repository.SecurityNoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityNoticesService {

    private final SecurityNoticesRepository securityNoticesRepository;

    @Transactional
    public void createSecurityNotice(SecurityNoticeCreateReq securityNoticeCreateReq) {
        if(securityNoticesRepository.findByTitle(securityNoticeCreateReq.getTitle()).isPresent()){
            throw new BaseException(ExceptionMessages.ERROR_SECURITYNOTICE_EXIST);
        }
        try{
            securityNoticesRepository.save(securityNoticeCreateReq.toEntity());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("보안공지 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<SecurityNoticeInquiryRes> getAllSecurityNotices() {
        return securityNoticesRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(SecurityNotices::getId).reversed())
                .map(SecurityNotices::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SecurityNotices getSingleSecurityNotice(Long id) {
        if(!securityNoticesRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_SECURITYNOTICE_NOT_EXIST);
        }
        return securityNoticesRepository
                .findById(id)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateSecurityNotice(SecurityNoticeUpdateReq securityNoticeUpdateReq) {
        if(!securityNoticesRepository.existsById(securityNoticeUpdateReq.getId())){
            throw new BaseException(ExceptionMessages.ERROR_SECURITYNOTICE_NOT_EXIST);
        }
        try {
            securityNoticesRepository.updateSecurityNotice(
                    securityNoticeUpdateReq.getId(),
                    securityNoticeUpdateReq.getTitle(),
                    securityNoticeUpdateReq.getContent()
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("새소식 업데이트에 실패했습니다.");
        }

    }

    @Transactional
    public void deleteSecurityNotice(Long id) {
        if(!securityNoticesRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_SECURITYNOTICE_NOT_EXIST);
        }
        try{
            securityNoticesRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("새소식 삭제에 실패했습니다.");
        }
    }


}
