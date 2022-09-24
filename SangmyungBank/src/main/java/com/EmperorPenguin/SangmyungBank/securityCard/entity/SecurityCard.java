package com.EmperorPenguin.SangmyungBank.securityCard.entity;
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

public class SecurityCard {

    @Id
    private Long securityCardPrivateNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @Column
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;
    private int number7;
    private int number8;
    private int number9;
    private int number10;
    private int number11;
    private int number12;

    public int getSecurityCardNumber(int num) {
        switch (num){
            case 1:
                return this.number1;
            case 2:
                return this.number2;
            case 3:
                return this.number3;
            case 4:
                return this.number4;
            case 5:
                return this.number5;
            case 6:
                return this.number6;
            case 7:
                return this.number7;
            case 8:
                return this.number8;
            case 9:
                return this.number9;
            case 10:
                return this.number10;
            case 11:
                return this.number11;
            case 12:
                return this.number12;
        }
        return -1;
    }
}

