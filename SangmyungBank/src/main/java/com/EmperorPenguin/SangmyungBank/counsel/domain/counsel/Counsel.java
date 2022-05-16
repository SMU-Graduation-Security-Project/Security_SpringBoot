package com.EmperorPenguin.SangmyungBank.counsel.domain.counsel;

import com.EmperorPenguin.SangmyungBank.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Counsel")
@Getter
@Setter
@NoArgsConstructor
public class Counsel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String title;
    private String content;
    private String user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Counsel(User loginId, String title, String content)
    {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

}
