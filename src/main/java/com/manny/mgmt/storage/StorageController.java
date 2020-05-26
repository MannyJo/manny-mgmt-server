package com.manny.mgmt.storage;

import com.manny.mgmt.storage.model.Food;
import com.manny.mgmt.storage.model.Section;
import com.manny.mgmt.storage.model.Storage;
import com.manny.mgmt.storage.service.FoodServiceImpl;
import com.manny.mgmt.storage.service.SectionServiceImpl;
import com.manny.mgmt.storage.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/storage")
@RestController
@CrossOrigin("*")
public class StorageController {

	
	@Autowired
	private StorageServiceImpl stService;
	
	@Autowired
	private SectionServiceImpl scService;
	
	@Autowired
	private FoodServiceImpl fService;

	@GetMapping("{userId}")
	public List<Storage> getStoragesByUserId(@PathVariable long userId) {
		return stService.findAllByUserId(userId);
	}
	
	@PostMapping("add")
	public void addStorage(@RequestBody Storage storage) {
		System.out.println(storage);
		stService.save(storage);
	}
	
	@PutMapping("update")
	public void updateStorage(@RequestBody Storage storage) {
		stService.save(storage);
	}

	@DeleteMapping("delete/{id}")
	public void deleteStorage(@PathVariable long id) {
		stService.delete(id);
	}
	
	@GetMapping("section/{storageId}")
	public List<Section> getAllSectionsByStorageId(@PathVariable long storageId) {
		return scService.findAllByStorageId(storageId);
	}
	
	@PostMapping("section/add")
	public void addSection(@RequestBody Section section) {
		scService.add(section);
	}
	
	@PutMapping("section/update")
	public void updateSection(@RequestBody Section section) {
		scService.update(section);
	}
	
	@DeleteMapping("section/delete/{id}")
	public void deleteSection(@PathVariable long id) {
		scService.delete(id);
	}
	
	@GetMapping("section/food/{sectionId}")
	public List<Food> getAllFoodBySectionId(@PathVariable long sectionId) {
		return fService.getAllFoodBySectionId(sectionId);
	}
	
	@PostMapping("section/food/add")
	public void addFood(@RequestBody Food food) {
		fService.add(food);
	}
	
	@PutMapping("section/food/update")
	public void updateFood(@RequestBody Food food) {
		fService.update(food);
	}
	
	@DeleteMapping("section/food/delete/{id}")
	public void deleteFood(@PathVariable long id) {
		fService.delete(id);
	}

}
