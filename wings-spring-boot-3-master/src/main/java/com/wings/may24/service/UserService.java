package com.wings.may24.service;

import com.wings.may24.entity.UserEntity;
import com.wings.may24.model.request.UserDetailsModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean userExists(String userId);
    void createUser(UserDetailsModel userDetailsModel);
    List<UserEntity> getUsers();
}
