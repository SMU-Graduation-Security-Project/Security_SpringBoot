package com.EmperorPenguin.SangmyungBank.counsel.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CounselException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselCreateReq;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselRequestRes;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselUpdateReq;
import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import com.EmperorPenguin.SangmyungBank.counsel.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CounselService {
    private final CounselRepository counselRepository;

    @Transactional
    public void createCounsel(CounselCreateReq counselCreateReq) {
        if (counselRepository.findByTitle(counselCreateReq.getTitle()).isPresent()) {
            throw new CounselException(ExceptionMessages.ERROR_EVENT_EXIST);
        }
        try {
            counselRepository.save(counselCreateReq.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CounselException("이벤트 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<CounselRequestRes> listAllCounsel() {
        return counselRepository.findAll()
                .stream()
                .map(Counsel::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Counsel getSingleCounsel (Long id) {
        if(!counselRepository.existsById(id)){
            throw new CounselException(ExceptionMessages.ERROR_COUNSEL_NOT_EXIST);
        }
        return counselRepository
                .findById(id)
                .orElseThrow(() -> new CounselException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateCounsel(CounselUpdateReq counselUpdateReq) {
        if(!counselRepository.existsById(counselUpdateReq.getId())){
            throw new CounselException(ExceptionMessages.ERROR_EVENT_NOT_EXIST);
        }
        try {
            counselRepository.updateCounsel(counselUpdateReq.getId(),counselUpdateReq.getTitle(),counselUpdateReq.getContent());
        }catch (Exception e){
            e.printStackTrace();
            throw new CounselException("상담글 업데이트에 실패했습니다.");
        }
    }

    @Transactional
    public void deleteCounsel(Long id) {
        if(!counselRepository.existsById(id)){
            throw new CounselException(ExceptionMessages.ERROR_COUNSEL_NOT_EXIST);
        }
        try {
            counselRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new CounselException("상담글 삭제에 실패했습니다.");
        }
    }
}
