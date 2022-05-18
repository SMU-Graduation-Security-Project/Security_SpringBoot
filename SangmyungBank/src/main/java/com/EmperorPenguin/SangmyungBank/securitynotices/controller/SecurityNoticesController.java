package com.EmperorPenguin.SangmyungBank.securitynotices.controller;

import com.EmperorPenguin.SangmyungBank.securitynotices.domain.securitynotices.SecurityNotices;
import com.EmperorPenguin.SangmyungBank.securitynotices.service.SecurityNoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class SecurityNoticesController {

    private final SecurityNoticesService securityNoticesService;

    // create security notice
    @PostMapping("/securitynotices")
    public ResponseEntity<HttpStatus> createSecurityNotice(@RequestBody SecurityNotices securityNotices) {
        securityNotices.setCreatedDate(LocalDateTime.now());
        securityNoticesService.createSecurityNotice(securityNotices);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // list all events
    @GetMapping("/securitynotices")
    public ResponseEntity<List<SecurityNotices>> listAllSecurityNotices() {
        List<SecurityNotices> securityNoticesList = securityNoticesService.listAllSecurityNotices();
        if (securityNoticesList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(securityNoticesList);
        }
    }

    // get security notice by id
    @GetMapping("/securitynotices/{id}")
    public ResponseEntity<SecurityNotices> getSecurityNoticeById(@PathVariable Long id) {
        SecurityNotices securityNotices = securityNoticesService.getSecurityNoticeById(id);
        if (securityNotices == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(securityNotices);
        }
    }

    // update security notice
    @PutMapping("/securitynotices/{id}")
    public ResponseEntity<HttpStatus> updateSecurityNotice(@PathVariable Long id, @RequestBody SecurityNotices securityNoticeDetails) {
        SecurityNotices securityNotices = securityNoticesService.updateSecurityNotice(id, securityNoticeDetails);
        if (securityNotices == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    // delete security notice
    @DeleteMapping("/securitynotices/{id}")
    public ResponseEntity<HttpStatus> deleteSecurityNotice(@PathVariable Long id) {
        SecurityNotices securityNotices = securityNoticesService.deleteNotice(id);
        if (securityNotices == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
}

