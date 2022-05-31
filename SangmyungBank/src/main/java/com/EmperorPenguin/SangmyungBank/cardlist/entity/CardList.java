package com.EmperorPenguin.SangmyungBank.cardlist.entity;

import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListInquiryRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CardList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", unique = true ,nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    public CardListInquiryRes toDto(){
        return CardListInquiryRes.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
