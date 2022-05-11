package com.EmperorPenguin.SangmyungBank.api.counsel.controller;

import com.EmperorPenguin.SangmyungBank.api.counsel.domain.counsel.Counsel;
import com.EmperorPenguin.SangmyungBank.api.counsel.domain.counselForm.CounselForm;
import com.EmperorPenguin.SangmyungBank.api.counsel.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class CounselController {

    private final CounselService counselService;

    @PostMapping("/counsel")
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody CounselForm counselForm) {
        counselService.createCounsel(counselForm);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/counsel")
    public ResponseEntity<List<Counsel>> listAllCustomers() {
        List<Counsel> counselList = counselService.listAllCounsel();
        if (counselList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(counselList);
        }
    }

    @GetMapping("/counsel/{id}")
    public ResponseEntity<Counsel> getCounselById(@PathVariable Long id) {
        Counsel counsel = counselService.getCounselById(id);
        if (counsel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(counsel);
        }
    }

    @PutMapping("/counsel/{id}")
    public ResponseEntity<HttpStatus> updateCounsel(@PathVariable Long id, @RequestBody CounselForm counselForm) {
        Counsel counsel = counselService.updateCounsel(id, counselForm);
        if (counsel == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @DeleteMapping("/counsel/{id}")
    public ResponseEntity<HttpStatus> deleteCounsel(@PathVariable Long id) {
        Counsel counsel = counselService.deleteCounsel(id);
        if (counsel == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
}
