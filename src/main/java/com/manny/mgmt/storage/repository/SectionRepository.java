package com.manny.mgmt.storage.repository;

import com.manny.mgmt.storage.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>, SectionRepositoryCustom {

}
