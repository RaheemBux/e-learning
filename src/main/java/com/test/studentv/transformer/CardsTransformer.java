package com.test.studentv.transformer;

import com.test.studentv.dto.CardsDTO;
import com.test.studentv.entity.CardsEntity;
import com.test.studentv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class CardsTransformer {

    public static CardsEntity getCardsEntity(CardsDTO cardsDTO){
        CardsEntity cardsEntity = new CardsEntity();
        if(cardsDTO.getId()!=null){
            cardsEntity.setId(Long.parseLong(cardsDTO.getId()));
        }
        if(cardsDTO.getQuestion()!=null){
            cardsEntity.setQuestion(cardsDTO.getQuestion());
        }
        if(cardsDTO.getAnswer()!=null){
            cardsEntity.setAnswer(cardsDTO.getAnswer());
        }
        if(cardsDTO.getCreatedBy()!=null){
            cardsEntity.setCreatedBy(Long.parseLong(cardsDTO.getCreatedBy()));
        }
        if(cardsDTO.getModifiedBy()!=null){
            cardsEntity.setModifiedBy(Long.parseLong(cardsDTO.getModifiedBy()));
        }
        cardsEntity.setCreatedDate(CommonUtil.getCurrentTimestamp());
        cardsEntity.setModifiedDate(CommonUtil.getCurrentTimestamp());

        if(cardsDTO.getStatus()!=null){
            cardsEntity.setStatus(Boolean.valueOf(cardsDTO.getStatus()));
        }
        return cardsEntity;
    }
    public static CardsDTO getCardsDTO(CardsEntity cardsEntity){
        CardsDTO cardsDTO = new CardsDTO();
        if(cardsEntity.getId()!=null){
            cardsDTO.setId(cardsEntity.getId().toString());
        }
        if(cardsEntity.getQuestion()!=null){
            cardsDTO.setQuestion(cardsEntity.getQuestion());
        }
        if(cardsEntity.getAnswer()!=null){
            cardsDTO.setAnswer(cardsEntity.getAnswer());
        }
        if(cardsEntity.getCreatedBy()!=null){
            cardsDTO.setCreatedBy(cardsEntity.getCreatedBy().toString());
        }
        if(cardsEntity.getModifiedBy()!=null){
            cardsDTO.setModifiedBy(cardsEntity.getModifiedBy().toString());
        }
        if(cardsEntity.getCreatedDate()!=null){
            cardsDTO.setCreatedDate(cardsEntity.getCreatedDate().toString());
        }
        if(cardsEntity.getModifiedDate()!=null){
            cardsDTO.setModifiedDate(cardsEntity.getModifiedDate().toString());
        }
        if(cardsEntity.getStatus()!=null){
            cardsDTO.setStatus(cardsEntity.getStatus().toString());
        }
        return cardsDTO;
    }
    public static List<CardsDTO> getCardsDTOS(List<CardsEntity> cardsEntities) {
        List<CardsDTO> cardsDTOS = new ArrayList<>();
        cardsEntities.forEach(cardsEntity -> {
            cardsDTOS.add(getCardsDTO(cardsEntity));
        });
        return cardsDTOS;
    }
}
