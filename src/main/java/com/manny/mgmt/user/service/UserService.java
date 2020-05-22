package com.manny.mgmt.user.service;

import com.manny.mgmt.common.model.ResponseObject;
import com.manny.mgmt.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserInfoById(long id);
    void createUser(User user);
    ResponseObject changePassword(User user);
    ResponseObject deleteUser(long id);

}
