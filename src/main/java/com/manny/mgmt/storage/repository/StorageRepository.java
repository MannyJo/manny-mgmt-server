package com.manny.mgmt.storage.repository;

import com.manny.mgmt.storage.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long>, StorageRepositoryCustom {

}
