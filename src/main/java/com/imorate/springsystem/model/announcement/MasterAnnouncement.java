package com.imorate.springsystem.model.announcement;

import com.imorate.springsystem.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master_announcement")
public class MasterAnnouncement extends AuditModel {

    @Id
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")
    private Date deadline;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Announcement announcement;
}
