package com.EmperorPenguin.SangmyungBank.news.entity;

import com.EmperorPenguin.SangmyungBank.news.dto.NewsInquiryRes;
import lombok.*;
import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News {
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

    public NewsInquiryRes toDto(){
        return NewsInquiryRes.builder()
                .id(id)
                .title(title)
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
