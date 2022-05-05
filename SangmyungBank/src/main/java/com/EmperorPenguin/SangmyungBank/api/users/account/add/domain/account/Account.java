package com.EmperorPenguin.SangmyungBank.api.users.account.add.domain.account;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account {

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column(columnDefinition = "text",nullable = false)
    private String accountPassword;

    @Column(nullable = false)
    private Long balance;

    @Builder
    public Account(User userId, String accountPassword, Long balance)
    {
        this.userId = userId;
        this.accountPassword = accountPassword;
        this.balance = balance;
    }
}
