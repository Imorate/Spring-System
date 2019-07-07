package com.imorate.springsystem.model.announcement;

import com.imorate.springsystem.model.AuditModel;
import com.imorate.springsystem.model.enumeration.SystemAnnouncementType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_announcement")
public class SystemAnnouncement extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "system_announcement_type", nullable = false)
    private SystemAnnouncementType systemAnnouncementType;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Announcement announcement;
}
