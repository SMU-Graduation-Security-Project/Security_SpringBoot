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
        if (select1 != select2)
            return true;
        else
            return false;
    }
}

