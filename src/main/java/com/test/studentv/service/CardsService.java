package com.test.studentv.service;

import com.test.studentv.entity.CardsEntity;

import java.util.List;

public interface CardsService {
    List<CardsEntity> findAll();
    CardsEntity create(CardsEntity cardsEntity) ;
    CardsEntity delete(CardsEntity cardsEntity);
    CardsEntity update(CardsEntity cardsEntity);
    CardsEntity findById(Long id);
}
