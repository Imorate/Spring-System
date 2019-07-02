package com.imorate.springsystem.model.auth;

import com.imorate.springsystem.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false, unique = true, length = 32)
    private String role;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<User> users;
}
