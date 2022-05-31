package com.EmperorPenguin.SangmyungBank.baseUtil.config;

import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Configuration
public class DateConfig {
    public String getDateTime(){
        return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));
    }


    public static String getExpireDate() {
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, +10);
        return dtf.format(cal.getTime());
    }
}
