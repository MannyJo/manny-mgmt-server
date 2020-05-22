package com.manny.mgmt.storage.repository;

import com.manny.mgmt.storage.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {

}
