package com.test.studentv.repository;

import com.test.studentv.entity.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardsRepository extends JpaRepository<CardsEntity, Long>, JpaSpecificationExecutor<CardsEntity> {
}
