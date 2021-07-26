package com.test.studentv.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
@Where(clause = "status = 1")
public class CourseEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "level")
    private String level;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "publicity_image")
    private String publicityImage;

    @Column(name = "completion")
    private String completion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "course_user",
            joinColumns = {
                    @JoinColumn(
                            name = "course_id",
                            nullable = false,
                            updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            nullable = false,
                            updatable = false)
            }
    )
    private Set<UserEntity> userEntities;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "course_card",
            joinColumns = {
                    @JoinColumn(
                            name = "course_id",
                            nullable = false,
                            updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "card_id",
                            nullable = false,
                            updatable = false)
            }
    )
    private Set<CardsEntity> cardsEntities;


}
