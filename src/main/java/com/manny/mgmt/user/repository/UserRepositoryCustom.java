package com.manny.mgmt.user.repository;

import com.manny.mgmt.user.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepositoryCustom {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> getUserInfoByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.password = :password, u.createdDate = now() " +
            "WHERE u.id = :id")
    int changePassword(
            @Param("password") String password,
            @Param("id") long id
    );
}
