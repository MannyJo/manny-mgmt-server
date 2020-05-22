package com.manny.mgmt.storage.service;

import com.manny.mgmt.storage.model.Storage;
import com.manny.mgmt.storage.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
	
	@Autowired
	private StorageRepository sRepository;

	@Override
	public Optional<Storage> findById(long id) {
		return sRepository.findById(id);
	}

	@Override
	public List<Storage> findAllByUserId(long userId) {
		return sRepository.findAllByUserId(userId);
	}

	@Override
	public void save(Storage storage) {
		sRepository.save(storage);
	}

	@Override
	public void delete(long id) {
		sRepository.deleteById(id);
	}

}
