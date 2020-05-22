package com.manny.mgmt.storage.service;

import com.manny.mgmt.storage.model.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageService {
	
	Optional<Storage> findById(long id);
	List<Storage> findAllByUserId(long userId);
	void save(Storage storage);
	void delete(long id);

}
