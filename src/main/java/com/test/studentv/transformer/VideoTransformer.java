package com.test.studentv.transformer;

import com.test.studentv.dto.VideoDTO;
import com.test.studentv.entity.VideoEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class VideoTransformer {

    public static VideoDTO getVideoDTO(VideoEntity videoEntity){
        VideoDTO videoDTO = new VideoDTO();

        if(videoEntity.getId()!=null){
            videoDTO.setId(videoEntity.getId().toString());
        }
        if(videoEntity.getTitle()!=null){
            videoDTO.setTitle(videoEntity.getTitle());
        }
        if(videoEntity.getOrder()!=null){
            videoDTO.setOrder(videoEntity.getOrder());
        }
        if(videoEntity.getSection()!=null){
            videoDTO.setSection(videoEntity.getSection());
        }
        if(videoEntity.getDescription()!=null){
            videoDTO.setDescription(videoEntity.getDescription());
        }
        if(videoEntity.getLength()!=null){
            videoDTO.setLength(videoEntity.getLength().toString());
        }
        if(videoEntity.getUrl()!=null){
            videoDTO.setUrl(videoEntity.getUrl());
        }
        if(videoEntity.getPdf()!=null){
            videoDTO.setPdf(videoEntity.getPdf());
        }
        if(videoEntity.getVideoPath()!=null){
            videoDTO.setVideoPath(videoEntity.getVideoPath());
        }
        if(videoEntity.getHashTagEntity()!=null){
            videoDTO.setHashTagDTO(HashTagTransformer.getHashTagDTO(videoEntity.getHashTagEntity()));
        }
        // five columns
        if(videoEntity.getCreatedBy()!=null){
            videoDTO.setCreatedBy(videoEntity.getCreatedBy().toString());
        }
        if(videoEntity.getModifiedBy()!=null){
            videoDTO.setModifiedBy(videoEntity.getModifiedBy().toString());
        }
        if(videoEntity.getCreatedDate()!=null){
            videoDTO.setCreatedDate(videoEntity.getCreatedDate().toString());
        }
        if(videoEntity.getModifiedDate()!=null){
            videoDTO.setModifiedDate(videoEntity.getModifiedDate().toString());
        }
        if(videoEntity.getStatus()!=null){
            videoDTO.setStatus(videoEntity.getStatus().toString());
        }

        return videoDTO;
    }
    public static VideoEntity getVideoEntity(VideoDTO videoDTO){
        VideoEntity videoEntity = new VideoEntity();

        if(videoDTO.getId()!=null){
            videoEntity.setId(Long.parseLong(videoDTO.getId()));
        }
        if(videoDTO.getTitle()!=null){
            videoEntity.setTitle(videoDTO.getTitle());
        }
        if(videoDTO.getOrder()!=null){
            videoEntity.setOrder(videoDTO.getOrder());
        }
        if(videoDTO.getSection()!=null){
            videoEntity.setSection(videoDTO.getSection());
        }
        if(videoDTO.getDescription()!=null){
            videoEntity.setDescription(videoDTO.getDescription());
        }
        if(videoDTO.getLength()!=null){
            videoEntity.setLength(Long.parseLong(videoDTO.getLength()));
        }
        if(videoDTO.getUrl()!=null){
            videoEntity.setUrl(videoDTO.getUrl());
        }
        if(videoDTO.getPdf()!=null){
            videoEntity.setPdf(videoDTO.getPdf());
        }
        if(videoDTO.getHashTagDTO()!=null){
            videoEntity.setHashTagEntity(HashTagTransformer.getHashTagEntity(videoDTO.getHashTagDTO()));
        }
        // five columns
        if(videoDTO.getCreatedBy()!=null){
            videoEntity.setCreatedBy(Long.parseLong(videoDTO.getCreatedBy()));
        }
        if(videoDTO.getModifiedBy()!=null){
            videoEntity.setModifiedBy(Long.parseLong(videoDTO.getModifiedBy()));
        }
        videoEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        videoEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(videoDTO.getStatus()!=null){
            videoEntity.setStatus(Boolean.parseBoolean(videoDTO.getStatus()));
        }

        return videoEntity;
    }
    public static List<VideoDTO> getVideoDTOs(List<VideoEntity> videoEntities) {
        List<VideoDTO> videoDTOS = new ArrayList<>();
        videoEntities.forEach(videoEntity -> {
            videoDTOS.add(VideoTransformer.getVideoDTO(videoEntity));
        });
        return videoDTOS;
    }
}
