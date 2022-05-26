package com.EmperorPenguin.SangmyungBank.counsel.entity;

import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselRequestRes;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
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
    @JoinColumn(name="userId")
    private User userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column
    private String createDate;

    @Column
    private String modifyDate;

    public CounselRequestRes toDto(){
        return CounselRequestRes.builder()
                .loginId(userId.getLoginId())
                .title(title)
                .content(content)
                .createDate(createDate)
                .modifyDate(modifyDate)
                .build();
    }
}
