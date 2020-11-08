package com.nearlog.user.domain.user;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
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

    // AuthenticationProvider에서 가져올 내용
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        if (userRole == null) {
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            auths.add(new SimpleGrantedAuthority(userRole.name()));
        }
        return auths;
    }

    public enum UserRole {
        USER, ADMIN
    }

    public enum JoinType {
        GOOGLE
    }

    public enum UserStatus {
        ENABLED, DISABLED,
    }

    @Builder
    public User(String email, String password, String nickname, String profile, UserRole userRole, JoinType joinType, UserStatus status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profile = profile;
        this.userRole = userRole;
        this.joinType = joinType;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
