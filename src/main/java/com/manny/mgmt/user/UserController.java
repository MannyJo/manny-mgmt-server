package com.manny.mgmt.user;

import com.manny.mgmt.common.model.ResponseObject;
import com.manny.mgmt.user.model.User;
import com.manny.mgmt.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("{id}")
    public User getUserInfo(@PathVariable("id") long id) {
        User user = userService.getUserInfoById(id).orElse(null);
        if(user != null) {
            user.setPassword("");
        }
        return user;
    }

    @PostMapping("create")
    public void createAccount(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("update/password")
    public ResponseEntity<ResponseObject> updateUserInfo(@RequestBody User user) {
        ResponseObject body = userService.changePassword(user);

        if(body.isSuccess()) {
            return ResponseEntity.ok().body(body);
        }

        return ResponseEntity.badRequest().body(body);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable("id") long id) {
        ResponseObject responseBody = userService.deleteUser(id);

        logger.info("Delete id : " + id);

        return ResponseEntity.ok(responseBody);
    }
}
