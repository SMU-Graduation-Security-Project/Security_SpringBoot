package com.EmperorPenguin.SangmyungBank.baseUtil.config;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {
    public String getDateTime(){
        return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd- MM:mm:ss"));
    }
}
