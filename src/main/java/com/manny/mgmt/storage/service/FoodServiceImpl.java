package com.manny.mgmt.storage.service;

import com.manny.mgmt.storage.model.Food;
import com.manny.mgmt.storage.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodRepository fRepository;

	@Override
	public List<Food> getAllFoodBySectionId(long sectionId) {
		return fRepository.getAllFoodBySectionId(sectionId);
	}

	@Override
	public void add(Food food) {
		fRepository.save(food);
	}

	@Override
	public void update(Food food) {
		fRepository.updateFood(food.getName(), food.getCount(), food.getMinCount(),
				food.getIsSetAlarm(), food.getPurchaseDate(), food.getId());
	}

	@Override
	public void delete(long id) {
		fRepository.deleteById(id);
	}

}
