package com.example.javajobjobproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Getter
@Entity
public class UserAccount extends AuditorField {
    @Id
    private String userId;

    @Column(nullable = false, length = 40)
    private String userPassword;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    private String nickname;
    @Setter
    @Column(length = 500)
    private String memo;


    protected UserAccount() {
    }

    private UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;

    }


    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccount(userId, userPassword, email, nickname, memo);

    }

}
