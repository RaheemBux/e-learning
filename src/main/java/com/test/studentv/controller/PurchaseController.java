package com.test.studentv.controller;

import com.test.studentv.dto.PurchaseDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.PurchaseEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.service.CourseService;
import com.test.studentv.service.PurchaseService;
import com.test.studentv.service.UserService;
import com.test.studentv.transformer.PurchaseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/purchaseCourse")
    public ResponseEntity<StatusDTO> purchaseCourse(@RequestBody PurchaseDTO purchaseDTO) {
        try {
            if(purchaseDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(purchaseDTO.getUserDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(purchaseDTO.getCourseDTO() != null) {
                CourseEntity courseEntity = courseService.findById(Long.parseLong(purchaseDTO.getCourseDTO().getId()));
                if (courseEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Course Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            PurchaseEntity purchaseEntity = PurchaseTransformer.getPurchaseEntity(purchaseDTO);
            purchaseEntity.setStatus(true);
            purchaseService.create(purchaseEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Course Purchased Successfully ",PurchaseTransformer.getPurchaseDTO(purchaseEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/findAllPurchasedCoursesByUser/{id}")
    public List<PurchaseDTO> findAllPurchasedCoursesByUser(@PathVariable Long id) {
        try {
            List<PurchaseEntity> purchaseEntities = purchaseService.findAllByUserEntityId(id);
            return PurchaseTransformer.getPurchaseDTOS(purchaseEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
