package com.test.studentv.controller;

import com.test.studentv.dto.CourseDTO;
import com.test.studentv.dto.PageDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.CardsEntity;
import com.test.studentv.entity.CategoryEntity;
import com.test.studentv.entity.ClassEntity;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.fileStorage.StorageService;
import com.test.studentv.searchFilters.CourseFilter;
import com.test.studentv.service.CardsService;
import com.test.studentv.service.CategoryService;
import com.test.studentv.service.ClassService;
import com.test.studentv.service.CourseService;
import com.test.studentv.transformer.CourseTransformer;
import com.test.studentv.util.PaginationUtil;
import com.test.studentv.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ClassService classService;

    @Autowired
    private CardsService cardsService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody CourseDTO courseDTO) {
        try {
            if(courseDTO.getCategoryDTO() != null) {
                CategoryEntity categoryEntity = categoryService.findById(Long.parseLong(courseDTO.getCategoryDTO().getId()));
                if (categoryEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Category Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(courseDTO.getClassDTO() != null) {
                ClassEntity classEntity = classService.findById(Long.parseLong(courseDTO.getClassDTO().getId()));
                if (classEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Class Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            CourseEntity courseEntity= CourseTransformer.getCourseEntity(courseDTO);
            courseEntity.setStatus(true);
            courseService.create(courseEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Course Added Successfully ",CourseTransformer.getCourseDTO(courseEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody CourseDTO courseDTO) {
        try {

            CourseEntity courseEntity=courseService.findById(Long.parseLong(courseDTO.getId()));
            if (courseEntity== null) {
                return new ResponseEntity<>(new StatusDTO(0, "Course not found!"), HttpStatus.NOT_FOUND);
            }
            if(courseDTO.getCategoryDTO() != null) {
                CategoryEntity categoryEntity = categoryService.findById(Long.parseLong(courseDTO.getCategoryDTO().getId()));
                if (categoryEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Category Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(courseDTO.getClassDTO() != null) {
                ClassEntity classEntity = classService.findById(Long.parseLong(courseDTO.getClassDTO().getId()));
                if (classEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Class Not Found"), HttpStatus.NOT_FOUND);
                }
            }

            courseEntity= CourseTransformer.getCourseEntity(courseDTO);
            courseEntity.setStatus(true);
            courseService.update(courseEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Course Updated Successfully ",CourseTransformer.getCourseDTO(courseEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<CourseDTO> viewById(@PathVariable Long id)  {
        CourseEntity courseEntity;
        CourseDTO courseDTO = null;
        try {
            courseEntity = courseService.findById(id);
            if (courseEntity != null) {
                courseDTO = CourseTransformer.getCourseDTO(courseEntity);
                return new ResponseEntity<>(courseDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Course not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            CourseEntity courseEntity = courseService.findById(id);

            if (courseEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Course not found!"), HttpStatus.NOT_FOUND);
            } else {
                if(courseEntity.getUserEntities()!=null){
                    return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Can't Delete assigned Course "), HttpStatus.NOT_FOUND);
                }
                courseService.delete(courseEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Course deleted Successfully"), HttpStatus.OK);
    }
    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<StatusDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            CourseEntity courseEntity = courseService.findById(id);
            if (courseEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Course not found!"), HttpStatus.NOT_FOUND);
            }
            courseEntity.setPublicityImage(storageService.store(image));
            courseService.update(courseEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Image Uploaded Successfully"), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAll")
    public List<CourseDTO> getAll() {
        List<CourseEntity> courseEntities =courseService.findAll();
        return CourseTransformer.getCourseDTOS(courseEntities);
    }
    @PostMapping(value = "/views")
    public PageDTO getAll(CourseFilter filter, @RequestBody PaginationUtil paginationUtil) {
        Map<String, String> params=new HashMap<>();
        params.put("page",paginationUtil.getCurrentPage().toString());
        params.put("itemsPerPage",paginationUtil.getItemsPerPages().toString());
        params.put("sortBy",paginationUtil.getSortBy());
        params.put("direction",paginationUtil.getDirection());
        Page<CourseEntity> page = courseService.findAllByFilterWithPaging(filter, Utility.createPageRequest(params));
        return new PageDTO(CourseTransformer.getCourseDTOS(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }

}
