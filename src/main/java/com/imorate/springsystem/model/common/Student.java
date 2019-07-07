package com.imorate.springsystem.model.common;

import com.imorate.springsystem.model.auth.User;
import lombok.*;

import javax.persistence.*;

@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    private Long id;

    @Column(name = "student_id", length = 8, nullable = false, unique = true)
    private Integer studentID;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}
