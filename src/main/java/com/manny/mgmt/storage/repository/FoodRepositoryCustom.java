package com.manny.mgmt.storage.repository;

import com.manny.mgmt.storage.model.Food;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FoodRepositoryCustom {
	
	@Query("SELECT f FROM Food f WHERE section_id = :sectionId ORDER BY f.id ASC")
    List<Food> getAllFoodBySectionId(@Param("sectionId") long sectionId);
	
	@Modifying
	@Query("UPDATE Food f "
			+ "SET f.name = :name, f.count = :count, f.minCount = :minCount, f.isSetAlarm = :isSetAlarm, f.purchaseDate = :purchaseDate "
			+ "WHERE f.id = :id")
    int updateFood(
            @Param("name") String name,
            @Param("count") int count,
            @Param("minCount") int minCount,
            @Param("isSetAlarm") boolean isSetAlarm,
            @Param("purchaseDate") Date purchaseDate,
            @Param("id") long id
    );

}
