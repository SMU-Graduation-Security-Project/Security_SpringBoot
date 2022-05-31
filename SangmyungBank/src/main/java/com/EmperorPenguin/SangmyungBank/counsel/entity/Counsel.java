package com.EmperorPenguin.SangmyungBank.counsel.entity;

import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselRequestRes;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
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
public class Counsel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String createDate;

    @Column
    private String modifyDate;

    public CounselRequestRes toDto(){
        return CounselRequestRes.builder()
                .loginId(memberId.getLoginId())
                .title(title)
                .content(content)
                .createDate(createDate)
                .modifyDate(modifyDate)
                .build();
    }
}