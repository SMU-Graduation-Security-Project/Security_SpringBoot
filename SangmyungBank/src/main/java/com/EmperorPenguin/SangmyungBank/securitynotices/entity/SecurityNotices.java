package com.EmperorPenguin.SangmyungBank.securitynotices.entity;

import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeRequestRes;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeUpdateReq;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SecurityNotices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", unique = true ,nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column
    private String createdDate;

    public SecurityNoticeRequestRes toDto(){
        return SecurityNoticeRequestRes.builder()
                .title(title)
                .context(content)
                .createdDate(createdDate)
                .build();
    }
}
