package com.nearlog.user.domain.user;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    @ToString.Exclude
    private String password;
    private String nickname;
    private String profile;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private JoinType joinType;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public enum UserRole {
        USER, ADMIN
    }

    public enum JoinType {
        GOOGLE
    }

    public enum UserStatus {
        ENABLED, DISABLED,
    }
}
