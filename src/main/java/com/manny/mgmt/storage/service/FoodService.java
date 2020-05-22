package com.manny.mgmt.storage.service;

import com.manny.mgmt.storage.model.Food;

import java.util.List;

public interface FoodService {
	
	List<Food> getAllFoodBySectionId(long sectionId);
	
	void add(Food food);
	
	void update(Food food);
	
	void delete(long id);

}
