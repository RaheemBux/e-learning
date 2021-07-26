package com.test.studentv.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
@Where(clause = "status = 1")
public class ClassEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "total_students")
    private String totalStudents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private VideoEntity videoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_course_id")
    private LikedCourseEntity likedCourseEntity;

}
