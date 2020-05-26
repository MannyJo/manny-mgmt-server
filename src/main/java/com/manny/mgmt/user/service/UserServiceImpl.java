package com.manny.mgmt.user.service;

import com.manny.mgmt.common.model.ResponseObject;
import com.manny.mgmt.user.model.Role;
import com.manny.mgmt.user.model.User;
import com.manny.mgmt.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

import static com.manny.mgmt.user.model.AppUserRole.*;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private static Logger logger = Logger.getLogger("UserServiceImpl");

    @Autowired
    private UserRepository uRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User searchedUser = uRepository.getUserInfoByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User %s not found.", username))
                );

        return checkUser(searchedUser);
    }

    private User checkUser(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        if(user.getRole().getId().equals(ADMIN.getId())) {
            user.setGrantedAuthorities(ADMIN.getGrantedAuthorities());
        } else {
            user.setGrantedAuthorities(USER.getGrantedAuthorities());
        }

        return user;
    }

    @Override
    public Optional<User> getUserInfoById(long id) {
        return uRepository.findById(id);
    }

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(new Role(USER.getId(), USER.name()));

        logger.info("User : " + user.toString());

        uRepository.save(user);
    }

    @Override
    public ResponseObject changePassword(User user) {
        ResponseObject responseObject = new ResponseObject(false, "Update failed.");
        Optional<User> userOptional = getUserInfoById(user.getId());

        if(!userOptional.isEmpty()) {
            boolean isPasswordMatched = passwordEncoder.matches(
                    user.getPassword(),
                    userOptional.get().getPassword()
            );

            logger.info("password match : " + isPasswordMatched);

            if(isPasswordMatched) {
                user.setNewPassword(passwordEncoder.encode(user.getNewPassword()));

                int update = uRepository.changePassword(
                        user.getNewPassword(),
                        user.getId()
                );

                logger.info("Update count : " + update);

                if(update > 0) {
                    responseObject.setSuccess(true);
                    responseObject.setMessage("Update is successful.");
                } else {
                    logger.info("Update failed.");
                }
            } else {
                logger.info("Password does not match.");
            }
        } else {
            logger.info("User does not exist.");
        }

        return responseObject;
    }

    @Override
    public ResponseObject deleteUser(long id) {
        ResponseObject responseObject = new ResponseObject(true, "User deleted.");
        try {
            uRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            responseObject.setSuccess(false);
            responseObject.setMessage("Delete failed.");
        }

        return responseObject;
    }
}
