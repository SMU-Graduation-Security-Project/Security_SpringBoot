package com.EmperorPenguin.SangmyungBank.winner.entity;

import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.winner.dto.WinnerRequestRes;
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
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="eventId")
    private Event eventId;

    private String content;

    private String createDate;

    public WinnerRequestRes toDto(){
        return WinnerRequestRes.builder()
                .id(id)
                .eventId(eventId.getId())
                .title(eventId.getTitle())
                .content(content)
                .createDate(createDate)
                .build();
    }
}
