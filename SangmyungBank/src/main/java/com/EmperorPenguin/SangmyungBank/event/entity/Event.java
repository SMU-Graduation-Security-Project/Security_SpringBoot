package com.EmperorPenguin.SangmyungBank.event.entity;

import com.EmperorPenguin.SangmyungBank.event.dto.EventInquiryRes;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String createDate;

    @Column
    private String startDate;

    @Column
    private String endDate;

    public EventInquiryRes toDto(){
        return EventInquiryRes.builder()
                .id(id)
                .title(title)
                .content(content)
                .createDate(createDate)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
