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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", unique = true ,nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column
    private String createdDate;

    public NewsRequestRes toDto(){
        return NewsRequestRes.builder()
                .title(title)
                .context(content)
                .createdDate(createdDate)
                .build();
    }
}
