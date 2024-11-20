package com.wings.may24.service.impl;

import com.wings.may24.entity.RoleEntity;
import com.wings.may24.entity.UserEntity;
import com.wings.may24.entity.UserPrincipal;
import com.wings.may24.exception.RoleNotFoundException;
import com.wings.may24.exception.UserAlreadyExistsException;
import com.wings.may24.model.request.UserDetailsModel;
import com.wings.may24.repository.RoleRepository;
import com.wings.may24.repository.UserRepository;
import com.wings.may24.security.SecurityConstants;
import com.wings.may24.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean userExists(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return userEntity != null;
    }

    @Override
    public void createUser(UserDetailsModel user) {

        if(userExists(user.getUserId()))
            throw new UserAlreadyExistsException();

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // Encrypt password
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Add roles
        Set<RoleEntity> roles = new HashSet<>();
        user.getRoles().forEach( role -> {

            // Fetch existing role from roles table
            RoleEntity roleEntity = roleRepository.findByName(SecurityConstants.ROLE_PREFIX + role.toUpperCase());

            if(roleEntity == null)
                throw new RoleNotFoundException(String.format("%s role is not allowed to set. Allowed roles: %s. Please contact administrator.", role,Arrays.toString(SecurityConstants.ALLOWED_ROLES.values())));
            roles.add(roleEntity);
        });

        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserId(username);

        if (userEntity == null)
            throw new UsernameNotFoundException("Username not found "+username);

        return new UserPrincipal(userEntity);
    }
}
