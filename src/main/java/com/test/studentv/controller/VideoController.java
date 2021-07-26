package com.test.studentv.controller;

import com.test.studentv.dto.StatusDTO;
import com.test.studentv.dto.VideoDTO;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.HashTagEntity;
import com.test.studentv.entity.VideoEntity;
import com.test.studentv.fileStorage.StorageService;
import com.test.studentv.service.HashTagService;
import com.test.studentv.service.VideoService;
import com.test.studentv.transformer.VideoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private HashTagService hashTagService;

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody VideoDTO videoDTO) {
        try {
            if(videoDTO.getHashTagDTO() != null) {
                HashTagEntity hashTagEntity = hashTagService.findById(Long.parseLong(videoDTO.getHashTagDTO().getId()));
                if (hashTagEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "HashTag Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            VideoEntity videoEntity = VideoTransformer.getVideoEntity(videoDTO);
            videoEntity.setStatus(true);
            videoService.create(videoEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Video Added Successfully ",VideoTransformer.getVideoDTO(videoEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody VideoDTO videoDTO) {
        try {
            VideoEntity videoEntity = videoService.findById(Long.parseLong(videoDTO.getId()));
            if (videoEntity== null) {
                return new ResponseEntity<>(new StatusDTO(0, "Video not found!"), HttpStatus.NOT_FOUND);
            }
            if(videoDTO.getHashTagDTO() != null) {
                HashTagEntity hashTagEntity = hashTagService.findById(Long.parseLong(videoDTO.getHashTagDTO().getId()));
                if (hashTagEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "HashTag Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            VideoEntity videoEntity1 = VideoTransformer.getVideoEntity(videoDTO);
            videoEntity1.setStatus(true);
            videoService.update(videoEntity1);

            return new ResponseEntity<>(new StatusDTO(1, "Video Updated Successfully ",VideoTransformer.getVideoDTO(videoEntity1)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<VideoDTO> viewById(@PathVariable Long id)  {
        VideoEntity videoEntity;
        VideoDTO videoDTO = null;
        try {
            videoEntity = videoService.findById(id);
            if (videoEntity != null) {
                videoDTO = VideoTransformer.getVideoDTO(videoEntity);
                return new ResponseEntity<>(videoDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Video not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            VideoEntity videoEntity = videoService.findById(id);

            if (videoEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Video not found!"), HttpStatus.NOT_FOUND);
            }
            videoService.delete(videoEntity);

        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Video deleted Successfully"), HttpStatus.OK);
    }
    @GetMapping(value = "/getAll")
    public List<VideoDTO> getAll() {
        List<VideoEntity> videoEntities = videoService.findAll();
        return VideoTransformer.getVideoDTOs(videoEntities);
    }
    @PostMapping("/uploadVideo/{id}")
    public ResponseEntity<StatusDTO> uploadVideo(
            @PathVariable Long id,
            @RequestParam("videoFile") MultipartFile videoFile
    ) {
        try {
            VideoEntity videoEntity = videoService.findById(id);
            if (videoEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Video ID not found!"), HttpStatus.NOT_FOUND);
            }
            videoEntity.setVideoPath(storageService.store(videoFile));
            videoService.update(videoEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Video Uploaded Successfully"), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }
}
