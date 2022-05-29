package com.EmperorPenguin.SangmyungBank.news.entity;

import com.EmperorPenguin.SangmyungBank.news.dto.NewsRequestRes;
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

    public NewsRequestRes toDto(){
        return NewsRequestRes.builder()
                .title(title)
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
