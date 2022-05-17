package com.EmperorPenguin.SangmyungBank.counsel.service;

import com.EmperorPenguin.SangmyungBank.counsel.domain.counsel.Counsel;
import com.EmperorPenguin.SangmyungBank.api.counsel.domain.counselForm.CounselForm;
import com.EmperorPenguin.SangmyungBank.counsel.domain.repository.CounselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CounselService {

    @Autowired
    private CounselRepository counselRepository;

    public List<Counsel> listAllCounsel() {
        List<Counsel> counselList = counselRepository.findAll();
        if (counselList.isEmpty())
            return null;
        return counselList;
    }

    public Counsel getCounselById(@PathVariable Long id) {
        Counsel counsel = counselRepository.findById(id)
                .orElse(null);

        return counsel;
    }

    public Counsel updateCounsel(@PathVariable Long id, @RequestBody CounselForm counselForm) {
        Counsel dbCounsel = counselRepository.findById(id)
                .orElse(null);
        if (dbCounsel != null) {
            dbCounsel.setTitle(counselForm.getTitle());
            dbCounsel.setContent(counselForm.getContent());
            return counselRepository.save(dbCounsel);
        }
        else {
            return null;
        }
    }

    public Counsel deleteCounsel(@PathVariable Long id) {
        Counsel counsel = counselRepository.findById(id)
                .orElse(null);
        if (counsel != null) {
            counselRepository.delete(counsel);
            return counsel;
        }
        else {
            return null;
        }
    }
}
