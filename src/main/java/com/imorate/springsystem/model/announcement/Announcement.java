package com.imorate.springsystem.model.announcement;

import com.imorate.springsystem.model.AuditModel;
import com.imorate.springsystem.model.auth.User;
import com.imorate.springsystem.model.enumeration.AnnouncementType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announcement")
public class Announcement extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "announcement_type")
    private AnnouncementType announcementType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
