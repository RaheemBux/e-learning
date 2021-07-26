package com.test.studentv.controller;

import com.test.studentv.dto.HashTagDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.ClassEntity;
import com.test.studentv.entity.HashTagEntity;
import com.test.studentv.service.ClassService;
import com.test.studentv.service.HashTagService;
import com.test.studentv.transformer.HashTagTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/hashTag")
public class HashTagController {

    @Autowired
    private HashTagService hashTagService;

    @Autowired
    private ClassService classService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody HashTagDTO hashTagDTO) {
        try {
            if(hashTagDTO.getClassDTO() != null) {
                ClassEntity classEntity = classService.findById(Long.parseLong(hashTagDTO.getClassDTO().getId()));
                if (classEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Class ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            HashTagEntity hashTagEntity = HashTagTransformer.getHashTagEntity(hashTagDTO);
            hashTagEntity.setStatus(true);
            hashTagService.create(hashTagEntity);

            return new ResponseEntity<>(new StatusDTO(1, "HashTag Added Successfully ",HashTagTransformer.getHashTagDTO(hashTagEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody HashTagDTO hashTagDTO) {
        try {
            HashTagEntity hashTagEntity = hashTagService.findById(Long.parseLong(hashTagDTO.getId()));
            if(hashTagEntity == null){
                return new ResponseEntity<>(new StatusDTO(0, "HashTag ID Not Found"), HttpStatus.NOT_FOUND);
            }
            if(hashTagDTO.getClassDTO() != null) {
                ClassEntity classEntity = classService.findById(Long.parseLong(hashTagDTO.getClassDTO().getId()));
                if (classEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Class ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            HashTagEntity hashTagEntity1 = HashTagTransformer.getHashTagEntity(hashTagDTO);
            hashTagEntity1.setStatus(true);
            hashTagService.update(hashTagEntity1);

            return new ResponseEntity<>(new StatusDTO(1, "HashTag Updated Successfully ",HashTagTransformer.getHashTagDTO(hashTagEntity1)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<HashTagDTO> viewById(@PathVariable Long id) throws IOException {
        HashTagEntity hashTagEntity;
        HashTagDTO hashTagDTO = null;
        try {
            hashTagEntity = hashTagService.findById(id);
            if (hashTagEntity != null) {
                hashTagDTO = HashTagTransformer.getHashTagDTO(hashTagEntity);
                return new ResponseEntity<>(hashTagDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("HashTag not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            HashTagEntity hashTagEntity = hashTagService.findById(id);

            if (hashTagEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "HashTag not found!"), HttpStatus.NOT_FOUND);
            } else {
                hashTagService.delete(hashTagEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "HashTag deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<HashTagDTO> getAll() {
        List<HashTagEntity> hashTagEntities = hashTagService.findAll();
        return HashTagTransformer.getHashTagDTOS(hashTagEntities);
    }
}
