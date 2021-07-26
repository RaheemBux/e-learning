package com.test.studentv.transformer;

import com.test.studentv.dto.CardsDTO;
import com.test.studentv.dto.CourseDTO;
import com.test.studentv.dto.UserDTO;
import com.test.studentv.entity.CardsEntity;
import com.test.studentv.entity.CourseEntity;
import com.test.studentv.entity.UserEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseTransformer {

    public static CourseDTO getCourseDTO(CourseEntity courseEntity){
        CourseDTO courseDTO=new CourseDTO();
        if(courseEntity.getId()!=null){
            courseDTO.setId(courseEntity.getId().toString());
        }
        if(courseEntity.getName()!=null){
            courseDTO.setName(courseEntity.getName());
        }
        if(courseEntity.getCode()!=null){
            courseDTO.setCode(courseEntity.getCode());
        }
        if(courseEntity.getLevel()!=null){
            courseDTO.setLevel(courseEntity.getLevel());
        }
        if(courseEntity.getGithubLink()!=null){
            courseDTO.setGithubLink(courseEntity.getGithubLink());
        }
        if(courseEntity.getPrice()!=null){
            courseDTO.setPrice(courseEntity.getPrice().toString());
        }
        if(courseEntity.getDescription()!=null){
            courseDTO.setDescription(courseEntity.getDescription());
        }
        if(courseEntity.getPublicityImage()!=null){
            courseDTO.setPublicityImage(courseEntity.getPublicityImage());
        }
        if(courseEntity.getCompletion()!=null){
            courseDTO.setCompletion(courseEntity.getCompletion());
        }
        if(courseEntity.getCategoryEntity()!=null){
            courseDTO.setCategoryDTO(CategoryTransformer.getCategoryDTO(courseEntity.getCategoryEntity()));
        }
        if(courseEntity.getClassEntity()!=null){
            courseDTO.setClassDTO(ClassTransformer.getClassDTO(courseEntity.getClassEntity()));
        }
        if(courseEntity.getCardsEntities()!=null){
            Set<CardsDTO> cardsDTOS = new HashSet<>();
            for(CardsEntity cardsEntity : courseEntity.getCardsEntities()){
                cardsDTOS.add(CardsTransformer.getCardsDTO(cardsEntity));
            }
            courseDTO.setCardsDTOS(cardsDTOS);
        }
        if(courseEntity.getUserEntities()!=null){
            Set<UserDTO> userDTOS = new HashSet<>();
            for(UserEntity userEntity : courseEntity.getUserEntities()){
                userDTOS.add(UserTransformer.getUserDTOWithoutCourse(userEntity));
            }
            courseDTO.setUserDTOS(userDTOS);
        }
        if(courseEntity.getCreatedBy()!=null){
            courseDTO.setCreatedBy(courseEntity.getCreatedBy().toString());
        }
        if(courseEntity.getModifiedBy()!=null){
            courseDTO.setModifiedBy(courseEntity.getModifiedBy().toString());
        }
        if(courseEntity.getCreatedDate()!=null){
            courseDTO.setCreatedDate(courseEntity.getCreatedDate().toString());
        }
        if(courseEntity.getModifiedDate()!=null){
            courseDTO.setModifiedDate(courseEntity.getModifiedDate().toString());
        }
        if(courseEntity.getStatus()!=null){
            courseDTO.setStatus(courseEntity.getStatus().toString());
        }

        return courseDTO;

    }
    public static CourseDTO getCourseDTOWithoutUser(CourseEntity courseEntity){
        CourseDTO courseDTO=new CourseDTO();
        if(courseEntity.getId()!=null){
            courseDTO.setId(courseEntity.getId().toString());
        }
        if(courseEntity.getName()!=null){
            courseDTO.setName(courseEntity.getName());
        }
        if(courseEntity.getCode()!=null){
            courseDTO.setCode(courseEntity.getCode());
        }
        if(courseEntity.getLevel()!=null){
            courseDTO.setLevel(courseEntity.getLevel());
        }
        if(courseEntity.getGithubLink()!=null){
            courseDTO.setGithubLink(courseEntity.getGithubLink());
        }
        if(courseEntity.getPrice()!=null){
            courseDTO.setPrice(courseEntity.getPrice().toString());
        }
        if(courseEntity.getDescription()!=null){
            courseDTO.setDescription(courseEntity.getDescription());
        }
        if(courseEntity.getPublicityImage()!=null){
            courseDTO.setPublicityImage(courseEntity.getPublicityImage());
        }
        if(courseEntity.getCompletion()!=null){
            courseDTO.setCompletion(courseEntity.getCompletion());
        }
        if(courseEntity.getCategoryEntity()!=null){
            courseDTO.setCategoryDTO(CategoryTransformer.getCategoryDTO(courseEntity.getCategoryEntity()));
        }
        if(courseEntity.getClassEntity()!=null){
            courseDTO.setClassDTO(ClassTransformer.getClassDTO(courseEntity.getClassEntity()));
        }
        if(courseEntity.getCardsEntities()!=null){
            Set<CardsDTO> cardsDTOS = new HashSet<>();
            for(CardsEntity cardsEntity : courseEntity.getCardsEntities()){
                cardsDTOS.add(CardsTransformer.getCardsDTO(cardsEntity));
            }
            courseDTO.setCardsDTOS(cardsDTOS);
        }
        if(courseEntity.getCreatedBy()!=null){
            courseDTO.setCreatedBy(courseEntity.getCreatedBy().toString());
        }
        if(courseEntity.getModifiedBy()!=null){
            courseDTO.setModifiedBy(courseEntity.getModifiedBy().toString());
        }
        if(courseEntity.getCreatedDate()!=null){
            courseDTO.setCreatedDate(courseEntity.getCreatedDate().toString());
        }
        if(courseEntity.getModifiedDate()!=null){
            courseDTO.setModifiedDate(courseEntity.getModifiedDate().toString());
        }
        if(courseEntity.getStatus()!=null){
            courseDTO.setStatus(courseEntity.getStatus().toString());
        }

        return courseDTO;

    }
    public static CourseEntity getCourseEntity(CourseDTO courseDTO){
        CourseEntity courseEntity=new CourseEntity();
        if(courseDTO.getId()!=null){
            courseEntity.setId(Long.parseLong(courseDTO.getId()));
        }
        if(courseDTO.getName()!=null){
            courseEntity.setName(courseDTO.getName());
        }
        if(courseDTO.getCode()!=null){
            courseEntity.setCode(courseDTO.getCode());
        }
        if(courseDTO.getLevel()!=null){
            courseEntity.setLevel(courseDTO.getLevel());
        }
        if(courseDTO.getGithubLink()!=null){
            courseEntity.setGithubLink(courseDTO.getGithubLink());
        }
        if(courseDTO.getPrice()!=null){
            courseEntity.setPrice(Double.parseDouble(courseDTO.getPrice()));
        }
        if(courseDTO.getDescription()!=null){
            courseEntity.setDescription(courseDTO.getDescription());
        }
        if(courseDTO.getCompletion()!=null){
            courseEntity.setCompletion(courseDTO.getCompletion());
        }
        if(courseDTO.getCreatedBy()!=null){
            courseEntity.setCreatedBy(Long.parseLong(courseDTO.getCreatedBy()));
        }
        if(courseDTO.getCategoryDTO()!=null){
            courseEntity.setCategoryEntity(CategoryTransformer.getCategoryEntity(courseDTO.getCategoryDTO()));
        }
        if(courseDTO.getClassDTO()!=null){
            courseEntity.setClassEntity(ClassTransformer.getClassEntity(courseDTO.getClassDTO()));
        }
        if(courseDTO.getCardsDTOS()!=null){
            Set<CardsEntity> cardsEntities = new HashSet<>();
            for(CardsDTO cardsDTO : courseDTO.getCardsDTOS()){
                cardsEntities.add(CardsTransformer.getCardsEntity(cardsDTO));
            }
            courseEntity.setCardsEntities(cardsEntities);
        }
        if(courseDTO.getUserDTOS()!=null){

            Set<UserEntity> userEntities = new HashSet<>();
            for(UserDTO userDTO : courseDTO.getUserDTOS()){
                userEntities.add(UserTransformer.getUserEntity(userDTO));
            }
            courseEntity.setUserEntities(userEntities);
        }
        if(courseDTO.getModifiedBy()!=null){
            courseEntity.setModifiedBy(Long.parseLong(courseDTO.getModifiedBy()));
        }
        courseEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        courseEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(courseDTO.getStatus()!=null){
            courseEntity.setStatus(Boolean.valueOf(courseDTO.getStatus()));
        }
        return courseEntity;

    }
    public static List<CourseDTO> getCourseDTOS(List<CourseEntity> courseEntities) {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        courseEntities.forEach(courseEntity -> {
            courseDTOS.add(CourseTransformer.getCourseDTO(courseEntity));
        });
        return courseDTOS;
    }
}
