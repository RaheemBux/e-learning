package com.test.studentv.controller;

import com.test.studentv.dto.CourseDTO;
import com.test.studentv.dto.PageDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.dto.UserDTO;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.entity.VideoEntity;
import com.test.studentv.fileStorage.StorageService;
import com.test.studentv.searchFilters.UserFilter;
import com.test.studentv.service.CourseService;
import com.test.studentv.service.UserService;
import com.test.studentv.service.VideoService;
import com.test.studentv.transformer.UserTransformer;
import com.test.studentv.util.PaginationUtil;
import com.test.studentv.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    StorageService storageService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getCourseDTOS() != null) {
                for (CourseDTO courseDTO : userDTO.getCourseDTOS()) {
                    CourseEntity courseEntity = courseService.findById(Long.parseLong(courseDTO.getId()));
                    if (courseEntity == null) {
                        return new ResponseEntity<>(new StatusDTO(0, "Course Not found with id :" + courseDTO.getId()), HttpStatus.NOT_FOUND);
                    }
                }
            }
            boolean error = false;
            String errorMsg = "";

            UserEntity oldUser = userService.findByUserName(userDTO.getUserName());
            if (oldUser != null) {
                if (oldUser.getStatus()) {
                    error = true;
                    errorMsg = userDTO.getUserName() + " userName already exists! ";
                }
            }
            oldUser = userService.findByEmail(userDTO.getEmail());
            if (oldUser != null) {
                error = true;
                errorMsg += userDTO.getEmail() + " emailId already exists! ";
            }
            oldUser = userService.findByMobileNo(userDTO.getMobileNo().toUpperCase());
            if (oldUser != null) {
                error = true;
                errorMsg += userDTO.getMobileNo() + " mobile# already exists! ";
            }
            if (error) {
                return new ResponseEntity<>(new StatusDTO(0, errorMsg), HttpStatus.OK);
            }
            if(!(userDTO.getEmail().contains("@"))){
                return new ResponseEntity<>(new StatusDTO(1, "Incorrect Email"), HttpStatus.OK);
            }

            UserEntity user = UserTransformer.getUserEntity(userDTO);
            user.setStatus(true);
            userService.create(user);
            return new ResponseEntity<>(new StatusDTO(1, "User Added Successfully ",UserTransformer.getUserDTO(user)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> updateUser(@RequestBody UserDTO userDTO) {
        try {
            boolean error = false;
            String errorMsg = "";
            UserEntity oldUser;
            UserEntity user;
            user=userService.findById(Long.parseLong(userDTO.getId()));
            if (user== null) {
                return new ResponseEntity<>(new StatusDTO(0, "User not found!"), HttpStatus.NOT_FOUND);
            } else {
                if (userDTO.getCourseDTOS() != null) {
                    for (CourseDTO courseDTO : userDTO.getCourseDTOS()) {
                        CourseEntity courseEntity = courseService.findById(Long.parseLong(courseDTO.getId()));
                        if (courseEntity == null) {
                            return new ResponseEntity<>(new StatusDTO(0, "Course Not found with id :" + courseDTO.getId()), HttpStatus.NOT_FOUND);
                        }
                    }
                }
                if (user.getUserName().equals(userDTO.getUserName()) && !(user.getUserName().equalsIgnoreCase(userDTO.getUserName()))) {
                    oldUser = userService.findByUserName(userDTO.getUserName());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getUserName() + " userName already exists! ";
                    }
                }
                if (user.getEmail().equals(userDTO.getEmail()) && !(user.getEmail().equalsIgnoreCase(userDTO.getEmail()))) {
                    oldUser = userService.findByEmail(userDTO.getEmail());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getEmail() + " emailId already exists! ";
                    }
                }
                if (user.getMobileNo().equals(userDTO.getMobileNo()) && !(user.getMobileNo().equalsIgnoreCase(userDTO.getMobileNo()))) {
                    oldUser = userService.findByMobileNo(userDTO.getMobileNo());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getMobileNo() + " mobile# already exists! ";
                    }
                }
                if (error) {
                    return new ResponseEntity<>(new StatusDTO(0, errorMsg), HttpStatus.OK);
                }
                user = UserTransformer.getUserEntity(userDTO);
                user.setStatus(true);
                userService.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StatusDTO(1, "User updated Successfully"), HttpStatus.OK);
    }
    @PostMapping(value = "/resetPassword")
    public ResponseEntity<StatusDTO> forgotPassword(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user;
            user=userService.findById(Long.parseLong(userDTO.getId()));
            if (user== null) {
                return new ResponseEntity<>(new StatusDTO(0, "User not found!"), HttpStatus.NOT_FOUND);
            } else {
                if(userDTO.getPassword()!=null){
                    user.setPassword(userDTO.getPassword());
                    user.setStatus(true);
                    userService.update(user);
                }
                else{
                    return new ResponseEntity<>(new StatusDTO(0, "Password is empty"), HttpStatus.NOT_FOUND);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StatusDTO(1, "Password updated Successfully"), HttpStatus.OK);
    }
    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<StatusDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            UserEntity user = userService.findById(id);
            if (user == null) {
                return new ResponseEntity<>(new StatusDTO(0, "User not found!"), HttpStatus.NOT_FOUND);
            }
            user.setImagePath( storageService.store(image));
            userService.update(user);
            return new ResponseEntity<>(new StatusDTO(1, "Image Uploaded Successfully"), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/view/{id}")
    public ResponseEntity<UserDTO> viewById(@PathVariable Long id) throws IOException {
        UserEntity user;
        UserDTO userDTO = null;
        try {
            user = userService.findById(id);
            if (user != null) {
                userDTO = UserTransformer.getUserDTO(user);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> deleteUser(@PathVariable Long id) {
        try {
            UserEntity user = userService.findById(id);

            if (user == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "User not found!"), HttpStatus.NOT_FOUND);
            } else {
                userService.delete(user);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "User deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities =userService.findAll();
        return UserTransformer.getUserDTOs(userEntities);
    }

    @PostMapping(value = "/views")
    public PageDTO getAll(UserFilter filter, @RequestBody PaginationUtil paginationUtil) {
        Map<String, String> params=new HashMap<>();
        params.put("page",paginationUtil.getCurrentPage().toString());
        params.put("itemsPerPage",paginationUtil.getItemsPerPages().toString());
        params.put("sortBy",paginationUtil.getSortBy());
        params.put("direction",paginationUtil.getDirection());
        Page<UserEntity> page = userService.findAllByFilterWithPaging(filter, Utility.createPageRequest(params));
        return new PageDTO(UserTransformer.getUserDTOs(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }
    @PostMapping("/askForVideo")
    public ResponseEntity<StatusDTO> askForVideo(Long userId,Long videoId) {
        try {
            UserEntity user = userService.findById(userId);
            if (user == null) {
                return new ResponseEntity<>(new StatusDTO(0, "User not found!"), HttpStatus.NOT_FOUND);
            }
            VideoEntity videoEntity = videoService.findById(videoId);
            if (videoEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Video not found!"), HttpStatus.NOT_FOUND);
            }
            user.setVideoEntity(videoEntity);
            userService.update(user);
            return new ResponseEntity<>(new StatusDTO(1, "Video Assigned Successfully"), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }

}