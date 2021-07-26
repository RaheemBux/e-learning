package com.test.studentv.controller;

import com.test.studentv.dto.CategoryDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.CategoryEntity;
import com.test.studentv.service.CategoryService;
import com.test.studentv.transformer.CategoryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody CategoryDTO categoryDTO) {
        try {

            CategoryEntity categoryEntity = CategoryTransformer.getCategoryEntity(categoryDTO);
            categoryEntity.setStatus(true);
            categoryService.create(categoryEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Category Added Successfully ",CategoryTransformer.getCategoryDTO(categoryEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody CategoryDTO categoryDTO) {
        try {

            CategoryEntity categoryEntity = categoryService.findById(Long.parseLong(categoryDTO.getId()));
            if (categoryEntity== null) {
                return new ResponseEntity<>(new StatusDTO(0, "Category not found!"), HttpStatus.NOT_FOUND);
            }

            categoryEntity= CategoryTransformer.getCategoryEntity(categoryDTO);
            categoryEntity.setStatus(true);
            categoryService.update(categoryEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Category Updated Successfully ",CategoryTransformer.getCategoryDTO(categoryEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<CategoryDTO> viewById(@PathVariable Long id)  {
        CategoryEntity categoryEntity;
        CategoryDTO categoryDTO = null;
        try {
            categoryEntity = categoryService.findById(id);
            if (categoryEntity != null) {
                categoryDTO = CategoryTransformer.getCategoryDTO(categoryEntity);
                return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Category not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            CategoryEntity categoryEntity = categoryService.findById(id);

            if (categoryEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Category not found!"), HttpStatus.NOT_FOUND);
            } else {
                categoryService.delete(categoryEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Category deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<CategoryDTO> getAll() {
        List<CategoryEntity> categoryEntities = categoryService.findAll();
        return CategoryTransformer.getCategoryDTOS(categoryEntities);
    }
}
