package com.EmperorPenguin.SangmyungBank.api.securitynotices.service;

import com.EmperorPenguin.SangmyungBank.api.securitynotices.domain.securitynotices.SecurityNotices;
import com.EmperorPenguin.SangmyungBank.api.securitynotices.domain.repository.SecurityNoticesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SecurityNoticesService {

    @Autowired
    private SecurityNoticesRepository securityNoticesRepository;

    public SecurityNotices createSecurityNotice(@RequestBody SecurityNotices securityNotices) {
        return securityNoticesRepository.save(securityNotices);
    }

    public List<SecurityNotices> listAllSecurityNotices() {
        List<SecurityNotices> securityNoticesList = securityNoticesRepository.findAll();
        if (securityNoticesList.isEmpty())
            return null;

        return securityNoticesList;
    }

    public SecurityNotices getSecurityNoticeById(@PathVariable Long id) {
        SecurityNotices securityNotices = securityNoticesRepository.findById(id)
                .orElse(null);

        return securityNotices;
    }

    public SecurityNotices updateSecurityNotice(@PathVariable Long id, @RequestBody SecurityNotices securityNoticeDetails) {
        SecurityNotices securityNotices = securityNoticesRepository.findById(id)
                .orElse(null);
        if (securityNotices != null) {
            securityNotices.setTitle(securityNoticeDetails.getTitle());
            securityNotices.setContent(securityNoticeDetails.getContent());

            SecurityNotices updateSecurityNotice = securityNoticesRepository.save(securityNotices);
            return updateSecurityNotice;
        }
        else {
            return null;
        }
    }

    public SecurityNotices deleteNotice(@PathVariable Long id) {
        SecurityNotices securityNotices = securityNoticesRepository.findById(id)
                .orElse(null);
        if (securityNotices != null) {
            securityNoticesRepository.delete(securityNotices);
            return securityNotices;
        }
        else {
            return null;
        }
    }
}
