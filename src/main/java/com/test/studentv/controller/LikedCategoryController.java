package com.test.studentv.controller;

import com.test.studentv.dto.LikedCategoryDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.CategoryEntity;
import com.test.studentv.entity.LikedCategoryEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.service.CategoryService;
import com.test.studentv.service.LikedCategoryService;
import com.test.studentv.service.UserService;
import com.test.studentv.transformer.LikedCategoryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/likedCategory")
public class LikedCategoryController {

    @Autowired
    private LikedCategoryService likedCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/likeCategory")
    public ResponseEntity<StatusDTO> likeCategory(@RequestBody LikedCategoryDTO likedCategoryDTO) {
        try {
            if(likedCategoryDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(likedCategoryDTO.getUserDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(likedCategoryDTO.getCategoryDTO() != null) {
                CategoryEntity categoryEntity = categoryService.findById(Long.parseLong(likedCategoryDTO.getCategoryDTO().getId()));
                if (categoryEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Category Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            LikedCategoryEntity likedCategoryEntity = LikedCategoryTransformer.getLikedCategoryEntity(likedCategoryDTO);
            likedCategoryEntity.setStatus(true);
            likedCategoryEntity.setIsLiked(true);
            likedCategoryService.create(likedCategoryEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Category Liked Successfully ",LikedCategoryTransformer.getLikedCategoryDTO(likedCategoryEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/getAllRecommendedCategories")
    public List<LikedCategoryDTO> getAllRecommendedCategories() {
        List<LikedCategoryEntity> likedCategoryEntities = likedCategoryService.findAll();
        return LikedCategoryTransformer.getLikedCategoryDTOs(likedCategoryEntities);
    }
    @GetMapping(value = "/findAllLikedCategoriesOfUser/{id}")
    public List<LikedCategoryDTO> findAllLikedCategoriesOfUser(@PathVariable Long id) {
        try {
            List<LikedCategoryEntity> likedCategoryEntities = likedCategoryService.findAllByUserEntityId(id);
            return LikedCategoryTransformer.getLikedCategoryDTOs(likedCategoryEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
