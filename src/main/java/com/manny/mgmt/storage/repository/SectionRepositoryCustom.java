package com.manny.mgmt.storage.repository;

import com.manny.mgmt.storage.model.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepositoryCustom {

	@Query("SELECT s FROM Section s WHERE storage_id = :storageId ORDER BY s.id ASC")
    List<Section> findAllByStorageId(@Param("storageId") long storageId);

}
