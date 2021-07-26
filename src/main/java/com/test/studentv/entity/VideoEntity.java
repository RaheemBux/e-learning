package com.test.studentv.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "video")
@Where(clause = "status = 1")
public class VideoEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String order;

    @Column(name = "title")
    private String title;

    @Column(name = "section")
    private String section;

    @Column(name = "description")
    private String description;

    @Column(name = "length")
    private Long length;

    @Column(name = "url")
    private String url;

    @Column(name = "pdf")
    private String pdf;

    @Column(name = "video_path")
    private String videoPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTagEntity hashTagEntity;
}
