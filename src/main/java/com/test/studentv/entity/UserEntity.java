package com.test.studentv.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user")
@Where(clause = "status = 1")
public class UserEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "type")
    private String type;

    @Column(name = "surname")
    private String surname;

    @Column(name = "version")
    private String version;

    @Column(name = "wifi")
    private String wifi;

    @Column(name = "quality")
    private String quality;

    @Column(name = "sex")
    private String sex;

    @Column(name = "job")
    private String job;

    @Column(name = "studies")
    private String studies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userEntities")
    private Set<CourseEntity> courseEntities = new HashSet<>();

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studentEntities")
    //private Set<ClassEntity> classEntities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private VideoEntity videoEntity;

}
