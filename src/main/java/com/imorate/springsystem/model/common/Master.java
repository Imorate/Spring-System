package com.imorate.springsystem.model.common;

import com.imorate.springsystem.model.auth.User;
import com.imorate.springsystem.model.enumeration.COLOR;
import lombok.*;

import javax.persistence.*;

@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master")
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "employee_id", nullable = false, unique = true)
    private Integer employeeID;

    @Enumerated(EnumType.STRING)
    @Column(name = "fallback_color", nullable = false)
    private COLOR fallbackColor;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @MapsId
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}
