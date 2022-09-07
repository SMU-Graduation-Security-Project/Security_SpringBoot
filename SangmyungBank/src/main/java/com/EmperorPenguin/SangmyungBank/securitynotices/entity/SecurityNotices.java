package com.EmperorPenguin.SangmyungBank.securitynotices.entity;

import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeInquiryRes;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SecurityNotices {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String createdDate;

    public SecurityNoticeInquiryRes toDto(){
        return SecurityNoticeInquiryRes.builder()
                .id(id)
                .title(title)
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
