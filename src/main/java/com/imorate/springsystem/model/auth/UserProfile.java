package com.imorate.springsystem.model.auth;

import com.imorate.springsystem.model.AuditModel;
import com.imorate.springsystem.model.enumeration.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile extends AuditModel implements Serializable {
    @Id
    private Long id;

    @Column(name = "first_name", length = 32)
    private String firstName;

    @Column(name = "last_name", length = 32)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @Column(name = "avatar", unique = true)
    private String avatar;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User user;

}
