package com.manny.mgmt.storage.repository;

import com.manny.mgmt.storage.model.Storage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageRepositoryCustom {
    @Query("SELECT s FROM Storage s LEFT JOIN s.users u WHERE u.id = :userId ORDER BY s.id ASC")
    List<Storage> findAllByUserId(@Param("userId") long userId);
}
