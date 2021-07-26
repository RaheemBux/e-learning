package com.test.studentv.controller;

import com.test.studentv.dto.StatusDTO;
import com.test.studentv.dto.WatcherDTO;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.entity.VideoEntity;
import com.test.studentv.entity.WatcherEntity;
import com.test.studentv.service.UserService;
import com.test.studentv.service.VideoService;
import com.test.studentv.service.WatcherService;
import com.test.studentv.transformer.WatcherTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/watcher")
public class WatcherController {

    @Autowired
    private WatcherService watcherService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody WatcherDTO watcherDTO) {
        try {
            if(watcherDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(watcherDTO.getUserDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(watcherDTO.getVideoDTO() != null) {
                VideoEntity videoEntity = videoService.findById(Long.parseLong(watcherDTO.getVideoDTO().getId()));
                if (videoEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Video ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            WatcherEntity watcherEntity = WatcherTransformer.getWatcherEntity(watcherDTO);
            watcherEntity.setStatus(true);
            watcherService.create(watcherEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Watcher Added Successfully ",WatcherTransformer.getWatcherDTO(watcherEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody WatcherDTO watcherDTO) {
        try {
            WatcherEntity watcherEntity = watcherService.findById(Long.parseLong(watcherDTO.getId()));
            if(watcherEntity == null){
                return new ResponseEntity<>(new StatusDTO(0, "Watcher ID Not Found"), HttpStatus.NOT_FOUND);
            }
            if(watcherDTO.getUserDTO() != null) {
                UserEntity userEntity = userService.findById(Long.parseLong(watcherDTO.getUserDTO().getId()));
                if (userEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "User ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            if(watcherDTO.getVideoDTO() != null) {
                VideoEntity videoEntity = videoService.findById(Long.parseLong(watcherDTO.getVideoDTO().getId()));
                if (videoEntity == null) {
                    return new ResponseEntity<>(new StatusDTO(0, "Video ID Not Found"), HttpStatus.NOT_FOUND);
                }
            }
            WatcherEntity watcherEntity1 = WatcherTransformer.getWatcherEntity(watcherDTO);
            watcherEntity1.setStatus(true);
            watcherService.update(watcherEntity1);

            return new ResponseEntity<>(new StatusDTO(1, "Watcher Updated Successfully ",WatcherTransformer.getWatcherDTO(watcherEntity1)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<WatcherDTO> viewById(@PathVariable Long id) throws IOException {
        WatcherEntity watcherEntity;
        WatcherDTO watcherDTO = null;
        try {
            watcherEntity = watcherService.findById(id);
            if (watcherEntity != null) {
                watcherDTO = WatcherTransformer.getWatcherDTO(watcherEntity);
                return new ResponseEntity<>(watcherDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Watcher not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            WatcherEntity watcherEntity = watcherService.findById(id);

            if (watcherEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Watcher not found!"), HttpStatus.NOT_FOUND);
            } else {
                watcherService.delete(watcherEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Watcher deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<WatcherDTO> getAll() {
        List<WatcherEntity> watcherEntities = watcherService.findAll();
        return WatcherTransformer.getWatcherDTOS(watcherEntities);
    }
}
