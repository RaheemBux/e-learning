package com.test.studentv.controller;

import com.test.studentv.dto.CardsDTO;
import com.test.studentv.dto.StatusDTO;
import com.test.studentv.entity.CardsEntity;
import com.test.studentv.service.CardsService;
import com.test.studentv.transformer.CardsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody CardsDTO cardsDTO) {
        try {
            CardsEntity cardsEntity = CardsTransformer.getCardsEntity(cardsDTO);
            cardsEntity.setStatus(true);
            cardsService.create(cardsEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Card Added Successfully ",CardsTransformer.getCardsDTO(cardsEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody CardsDTO cardsDTO) {
        try {
            CardsEntity cardsEntity = cardsService.findById(Long.parseLong(cardsDTO.getId()));
            if(cardsEntity == null){
                return new ResponseEntity<>(new StatusDTO(0, "Card not found!"), HttpStatus.NOT_FOUND);
            }
            cardsEntity = CardsTransformer.getCardsEntity(cardsDTO);
            cardsEntity.setStatus(true);
            cardsService.create(cardsEntity);

            return new ResponseEntity<>(new StatusDTO(1, "Card Updated Successfully ",CardsTransformer.getCardsDTO(cardsEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<CardsDTO> viewById(@PathVariable Long id)  {
        CardsEntity cardsEntity;
        CardsDTO cardsDTO = null;
        try {
            cardsEntity = cardsService.findById(id);
            if (cardsEntity != null) {
                cardsDTO = CardsTransformer.getCardsDTO(cardsEntity);
                return new ResponseEntity<>(cardsDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Card not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            CardsEntity cardsEntity = cardsService.findById(id);

            if (cardsEntity == null) {
                return new ResponseEntity<StatusDTO>(new StatusDTO(1, "Card not found!"), HttpStatus.NOT_FOUND);
            } else {
                cardsService.delete(cardsEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Card deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<CardsDTO> getAll() {
        List<CardsEntity> cardsEntities = cardsService.findAll();
        return CardsTransformer.getCardsDTOS(cardsEntities);
    }
}
