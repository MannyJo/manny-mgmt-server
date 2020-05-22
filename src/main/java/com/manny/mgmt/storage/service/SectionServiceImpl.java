package com.manny.mgmt.storage.service;

import com.manny.mgmt.storage.model.Section;
import com.manny.mgmt.storage.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
	
	@Autowired
	private SectionRepository sRepository;

	/**
	 * This method is from custom repository.
	 */
	@Override
	public List<Section> findAllByStorageId(long storageId) {
		System.out.println(sRepository.findAllByStorageId(storageId));
		return sRepository.findAllByStorageId(storageId);
	}

	@Override
	public void update(Section section) {
		sRepository.save(section);
	}

	@Override
	public void add(Section section) {
		sRepository.save(section);
	}

	@Override
	public void delete(long id) {
		sRepository.deleteById(id);
	}

}
