package com.EmperorPenguin.SangmyungBank.securityCard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityCardRandomRes {
    private int select1;
    private int select2;
    
    public boolean checkNumber() {
        this.select1 += 1;
        this.select2 += 1;
        return select1 != select2;
    }
}

