package com.manny.mgmt.storage.service;

import com.manny.mgmt.storage.model.Section;

import java.util.List;

public interface SectionService {
	
	List<Section> findAllByStorageId(long storageId);
	
	void update(Section section);
	
	void add(Section section);
	
	void delete(long id);

}
