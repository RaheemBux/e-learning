package com.test.studentv.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "message")
@Where(clause = "status = 1")
public class MessageEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "sender_id")
    private UserEntity senderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "receiver_id")
    private UserEntity receiverEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "video_id")
    private VideoEntity videoEntity;
}
