package com.EmperorPenguin.SangmyungBank.event.entity;

import com.EmperorPenguin.SangmyungBank.event.dto.EventRequestRes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", unique = true ,nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column
    private String writeDate;

    @Column
    private String startDate;

    @Column
    private String endDate;

    public EventRequestRes toDto(){
        return EventRequestRes.builder()
                .title(title)
                .content(content)
                .writeDate(writeDate)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
