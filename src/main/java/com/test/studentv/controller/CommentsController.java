package com.test.studentv.controller;

import com.test.studentv.dto.CommentsDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.*;
import com.test.studentv.service.CommentsService;
import com.test.studentv.service.CourseService;
import com.test.studentv.service.UserService;
import com.test.studentv.transformer.CommentsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody CommentsDTO commentsDTO) {
        try {
            if(commentsDTO.getCourseDTO() != null) {
                CourseEntity courseEntity = courseService.findById(Long.parseLong(commentsDTO.getCourseDTO().getId()));
                if (courseEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Course ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(commentsDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(commentsDTO.getUserDTO() .getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            CommentsEntity commentsEntity = CommentsTransformer.getCommentsEntity(commentsDTO);
            commentsEntity.setStatus(true);
            commentsService.create(commentsEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Comments Added Successfully ",CommentsTransformer.getCommentsDTO(commentsEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody CommentsDTO commentsDTO) {
        try {
            CommentsEntity commentsEntity = commentsService.findById(Long.parseLong(commentsDTO.getId()));
            if(commentsEntity == null){
                return new ResponseEntity<>(new StatusDTO(0, "Comments ID Not Found"), HttpStatus.NOT_FOUND);
            }
            if(commentsDTO.getCourseDTO() != null) {
                CourseEntity courseEntity = courseService.findById(Long.parseLong(commentsDTO.getCourseDTO().getId()));
                if (courseEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Course ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(commentsDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(commentsDTO.getUserDTO() .getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            CommentsEntity commentsEntity1 = CommentsTransformer.getCommentsEntity(commentsDTO);
            commentsEntity1.setStatus(true);
            commentsService.update(commentsEntity1);

            return new ResponseEntity<>(new StatusDTO(1, "Comments Updated Successfully ",CommentsTransformer.getCommentsDTO(commentsEntity1)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<CommentsDTO> viewById(@PathVariable Long id) throws IOException {
        CommentsEntity commentsEntity;
        CommentsDTO commentsDTO = null;
        try {
            commentsEntity = commentsService.findById(id);
            if (commentsEntity != null) {
                commentsDTO = CommentsTransformer.getCommentsDTO(commentsEntity);
                return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Comment not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            CommentsEntity commentsEntity = commentsService.findById(id);

            if (commentsEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Comment not found!"), HttpStatus.NOT_FOUND);
            } else {
                commentsService.delete(commentsEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Comment deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<CommentsDTO> getAll() {
        List<CommentsEntity> commentsEntities =commentsService.findAll();
        return CommentsTransformer.getCommentsDTOs(commentsEntities);
    }
    @GetMapping(value = "/getAllByCourseId/{id}")
    public List<CommentsDTO> getAllByCourseId(@PathVariable  Long id) {
        List<CommentsEntity> commentsEntities =commentsService.findAllByCourseEntityId(id);
        return CommentsTransformer.getCommentsDTOs(commentsEntities);
    }
}
