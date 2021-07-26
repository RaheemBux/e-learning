package com.test.studentv.controller;

import com.test.studentv.dto.LikedCourseDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.LikedCourseEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.service.CourseService;
import com.test.studentv.service.LikedCourseService;
import com.test.studentv.service.UserService;
import com.test.studentv.transformer.LikedCourseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/likedCourse")
public class LikedCourseController {

    @Autowired
    private LikedCourseService likedCourseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/likeCourse")
    public ResponseEntity<StatusDTO> likeCourse(@RequestBody LikedCourseDTO likedCourseDTO) {
        try {
            if(likedCourseDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(likedCourseDTO.getUserDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(likedCourseDTO.getCourseDTO() != null) {
                CourseEntity courseEntity = courseService.findById(Long.parseLong(likedCourseDTO.getCourseDTO().getId()));
                if (courseEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Course Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            LikedCourseEntity likedCourseEntity= LikedCourseTransformer.getLikedCourseEntity(likedCourseDTO);
            likedCourseEntity.setStatus(true);
            likedCourseEntity.setIsLiked(true);
            likedCourseService.create(likedCourseEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Course Liked Successfully ",LikedCourseTransformer.getLikedCourseDTO(likedCourseEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/getAllRecommendedCourses")
    public List<LikedCourseDTO> getAllRecommendedCourses() {
        List<LikedCourseEntity> likedCourseEntities = likedCourseService.findAll();
        return LikedCourseTransformer.getLikedCourseDTOS(likedCourseEntities);
    }
    @GetMapping(value = "/findAllLikedCoursesOfUser/{id}")
    public List<LikedCourseDTO> findAllLikedCoursesOfUser(@PathVariable Long id) {
        try {
            List<LikedCourseEntity> likedCourseEntities = likedCourseService.findAllByUserEntityId(id);
            return LikedCourseTransformer.getLikedCourseDTOS(likedCourseEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping(value = "/getAllRecommendedCoursesByCategory")
    public List<LikedCourseDTO> getAllRecommendedCoursesByCategory() {
        List<LikedCourseEntity> likedCourseEntities =likedCourseService.findAll();
        List<LikedCourseDTO> likedCourseDTOS = new ArrayList<>();
        for(LikedCourseEntity likedCourseEntity : likedCourseEntities){
            if(likedCourseEntity.getCourseEntity()!=null){
                if(likedCourseEntity.getCourseEntity().getCategoryEntity()!=null){
                    likedCourseDTOS.add(LikedCourseTransformer.getLikedCourseDTO(likedCourseEntity));
                }
            }
        }
        return likedCourseDTOS;
    }
    @GetMapping(value = "/getAllRecommendedCoursesByHashTag")
    public List<LikedCourseDTO> getAllRecommendedCoursesByHashTag() {
        List<LikedCourseEntity> likedCourseEntities =likedCourseService.findAll();
        List<LikedCourseDTO> likedCourseDTOS = new ArrayList<>();
        for(LikedCourseEntity likedCourseEntity : likedCourseEntities){
            if(likedCourseEntity.getCourseEntity()!=null){
                if(likedCourseEntity.getCourseEntity().getClassEntity()!=null){
                    if(likedCourseEntity.getCourseEntity().getClassEntity().getVideoEntity()!=null){
                        if(likedCourseEntity.getCourseEntity().getClassEntity().getVideoEntity().getHashTagEntity()!=null){
                            likedCourseDTOS.add(LikedCourseTransformer.getLikedCourseDTO(likedCourseEntity));
                        }
                    }
                }
            }
        }
        return likedCourseDTOS;
    }
}
