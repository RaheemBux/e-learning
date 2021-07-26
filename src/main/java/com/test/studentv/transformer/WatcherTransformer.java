package com.test.studentv.transformer;

import com.test.studentv.dto.WatcherDTO;
import com.test.studentv.entity.WatcherEntity;

import java.util.ArrayList;
import java.util.List;

public class WatcherTransformer {

    public static WatcherEntity getWatcherEntity(WatcherDTO watcherDTO){
        WatcherEntity watcherEntity = new WatcherEntity();

        if(watcherDTO.getId()!=null){
            watcherEntity.setId(Long.parseLong(watcherDTO.getId()));
        }
        if(watcherDTO.getMin()!=null){
            watcherEntity.setMin(watcherDTO.getMin());
        }
        if(watcherDTO.getSec()!=null){
            watcherEntity.setSec(watcherDTO.getSec());
        }
        if(watcherDTO.getTotalTime()!=null){
            watcherEntity.setTotalTime(watcherDTO.getTotalTime());
        }
        if(watcherDTO.getUserDTO()!=null){
            watcherEntity.setUserEntity(UserTransformer.getUserEntity(watcherDTO.getUserDTO()));
        }
        if(watcherDTO.getVideoDTO()!=null){
            watcherEntity.setVideoEntity(VideoTransformer.getVideoEntity(watcherDTO.getVideoDTO()));
        }

        return watcherEntity;
    }
    public static WatcherDTO getWatcherDTO(WatcherEntity watcherEntity){
        WatcherDTO watcherDTO = new WatcherDTO();

        if(watcherEntity.getId()!=null){
            watcherDTO.setId(watcherEntity.getId().toString());
        }
        if(watcherEntity.getMin()!=null){
            watcherDTO.setMin(watcherDTO.getMin());
        }
        if(watcherEntity.getSec()!=null){
            watcherDTO.setSec(watcherEntity.getSec());
        }
        if(watcherEntity.getTotalTime()!=null){
            watcherDTO.setTotalTime(watcherEntity.getTotalTime());
        }
        if(watcherEntity.getUserEntity()!=null){
            watcherDTO.setUserDTO(UserTransformer.getUserDTO(watcherEntity.getUserEntity()));
        }
        if(watcherEntity.getVideoEntity()!=null){
            watcherDTO.setVideoDTO(VideoTransformer.getVideoDTO(watcherEntity.getVideoEntity()));
        }

        return watcherDTO;
    }
    public static List<WatcherDTO> getWatcherDTOS(List<WatcherEntity> watcherEntities) {

        List<WatcherDTO> watcherDTOS = new ArrayList<>();
        watcherEntities.forEach(watcherEntity -> {
            watcherDTOS.add(getWatcherDTO(watcherEntity));
        });
        return watcherDTOS;
    }
}
