package com.test.studentv.serviceImpl;

import com.test.studentv.entity.CardsEntity;
import com.test.studentv.repository.CardsRepository;
import com.test.studentv.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardsServiceImpl implements CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public List<CardsEntity> findAll() {
        return cardsRepository.findAll();
    }

    @Override
    public CardsEntity create(CardsEntity cardsEntity) {
        return cardsRepository.save(cardsEntity);
    }

    @Override
    public CardsEntity delete(CardsEntity cardsEntity) {
        if (cardsEntity.getId() != null) {
            cardsEntity.setStatus(false);
            cardsRepository.save(cardsEntity);
            return cardsEntity;
        }
        return null;
    }

    @Override
    public CardsEntity update(CardsEntity cardsEntity) {
        if (cardsEntity.getId() != null) {
            CardsEntity persisted = findById(cardsEntity.getId());
            if (persisted == null) {
                return null;
            }
            CardsEntity updated = cardsRepository.save(cardsEntity);
            return updated;
        }
        return null;
    }

    @Override
    public CardsEntity findById(Long id) {
        Optional<CardsEntity> optionalUser =  cardsRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }
}
