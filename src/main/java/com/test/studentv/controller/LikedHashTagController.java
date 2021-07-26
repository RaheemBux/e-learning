package com.test.studentv.controller;

import com.test.studentv.dto.LikedHashTagDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.HashTagEntity;
import com.test.studentv.entity.LikedHashTagEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.service.HashTagService;
import com.test.studentv.service.LikedHashTagService;
import com.test.studentv.service.UserService;
import com.test.studentv.transformer.LikedHashTagTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/likedHashTag")
public class LikedHashTagController {

    @Autowired
    private LikedHashTagService likedHashTagService;

    @Autowired
    private UserService userService;

    @Autowired
    private HashTagService hashTagService;

    @PostMapping(value = "/likeHashTag")
    public ResponseEntity<StatusDTO> likeHashTag(@RequestBody LikedHashTagDTO likedHashTagDTO) {
        try {
            if(likedHashTagDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(likedHashTagDTO.getUserDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(likedHashTagDTO.getHashTagDTO() != null) {
                HashTagEntity hashTagEntity = hashTagService.findById(Long.parseLong(likedHashTagDTO.getHashTagDTO().getId()));
                if (hashTagEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "HashTag Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            LikedHashTagEntity likedHashTagEntity = LikedHashTagTransformer.getLikedHashTagEntity(likedHashTagDTO);
            likedHashTagEntity.setStatus(true);
            likedHashTagEntity.setIsLiked(true);
            likedHashTagService.create(likedHashTagEntity);

            return new ResponseEntity<>(new StatusDTO(1, "HashTag Liked Successfully ",LikedHashTagTransformer.getLikedHashTagDTO(likedHashTagEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/getAllRecommendedHashTags")
    public List<LikedHashTagDTO> getAllRecommendedHashTags() {
        List<LikedHashTagEntity> likedHashTagEntities = likedHashTagService.findAll();
        return LikedHashTagTransformer.getLikedHashTagDTOs(likedHashTagEntities);
    }
    @GetMapping(value = "/findAllLikedHashTagsOfUser/{id}")
    public List<LikedHashTagDTO> findAllLikedHashTagsOfUser(@PathVariable Long id) {
        try {
            List<LikedHashTagEntity> likedHashTagEntities = likedHashTagService.findAllByUserEntityId(id);
            return LikedHashTagTransformer.getLikedHashTagDTOs(likedHashTagEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
