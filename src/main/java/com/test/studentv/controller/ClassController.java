package com.test.studentv.controller;

import com.test.studentv.dto.ClassDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.*;
import com.test.studentv.service.ClassService;
import com.test.studentv.service.LikedCourseService;
import com.test.studentv.service.VideoService;
import com.test.studentv.transformer.ClassTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @Autowired
    private LikedCourseService likedCourseService;

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody ClassDTO classDTO) {
        try {
            if(classDTO.getLikedCourseDTO() != null) {
                LikedCourseEntity likedCourseEntity = likedCourseService.findById(Long.parseLong(classDTO.getLikedCourseDTO().getId()));
                if (likedCourseEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Liked Course ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(classDTO.getVideoDTO() != null) {
                VideoEntity videoEntity = videoService.findById(Long.parseLong(classDTO.getVideoDTO().getId()));
                if (videoEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Video ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            ClassEntity classEntity= ClassTransformer.getClassEntity(classDTO);
            classEntity.setStatus(true);
            classService.create(classEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Class Added Successfully ",ClassTransformer.getClassDTO(classEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody ClassDTO classDTO) {
        try {
            ClassEntity classEntity=classService.findById(Long.parseLong(classDTO.getId()));
            if (classEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Class not found!"), HttpStatus.NOT_FOUND);
            }
            if(classDTO.getLikedCourseDTO() != null) {
                LikedCourseEntity likedCourseEntity = likedCourseService.findById(Long.parseLong(classDTO.getLikedCourseDTO().getId()));
                if (likedCourseEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Liked Course ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(classDTO.getVideoDTO() != null) {
                VideoEntity videoEntity = videoService.findById(Long.parseLong(classDTO.getVideoDTO().getId()));
                if (videoEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Video ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            classEntity= ClassTransformer.getClassEntity(classDTO);
            classEntity.setStatus(true);
            classService.update(classEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Class Updated Successfully ",ClassTransformer.getClassDTO(classEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<ClassDTO> viewById(@PathVariable Long id) throws IOException {
        ClassEntity classEntity;
        ClassDTO classDTO = null;
        try {
            classEntity = classService.findById(id);
            if (classEntity != null) {
                classDTO = ClassTransformer.getClassDTO(classEntity);
                return new ResponseEntity<>(classDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Classs not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            ClassEntity classEntity = classService.findById(id);

            if (classEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Class not found!"), HttpStatus.NOT_FOUND);
            } else {
                classService.delete(classEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Class deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<ClassDTO> getAll() {
        List<ClassEntity> classEntities =classService.findAll();
        return ClassTransformer.getClassDTOs(classEntities);
    }

}
