package com.EmperorPenguin.SangmyungBank.counsel.entity;

import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselRequestRes;
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
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="loginId", referencedColumnName = "loginId")
//    private User loginId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", unique = true ,nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column
    private String createDate;

    public CounselRequestRes toDto(){
        return CounselRequestRes.builder()
                .title(title)
                .content(content)
                .createDate(createDate)
                .build();
    }
}
