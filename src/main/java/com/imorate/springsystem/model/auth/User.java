package com.imorate.springsystem.model.auth;

import com.imorate.springsystem.model.AuditModel;
import com.imorate.springsystem.model.announcement.Announcement;
import com.imorate.springsystem.model.common.Master;
import com.imorate.springsystem.model.common.Student;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Set;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_enable", nullable = false)
    private boolean enable;

    @Column(name = "is_account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "is_account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserProfile userProfile;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Master master;

    @ManyToMany
    @ToString.Exclude
    private Set<Role> roles;

}
