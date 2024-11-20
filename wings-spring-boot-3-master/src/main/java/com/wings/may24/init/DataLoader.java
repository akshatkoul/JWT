package com.wings.may24.init;

import com.wings.may24.entity.RoleEntity;
import com.wings.may24.entity.UserEntity;
import com.wings.may24.repository.RoleRepository;
import com.wings.may24.repository.UserRepository;
import com.wings.may24.security.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @EventListener
    @Transactional
    public void loadData(ApplicationReadyEvent applicationReadyEvent){
        UserEntity userEntity = new UserEntity();
        Set<RoleEntity> roles = new HashSet<>();

        var allowedRoles = Arrays.stream(SecurityConstants.ALLOWED_ROLES.values());
        allowedRoles.forEach(role ->
                {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setName(SecurityConstants.ROLE_PREFIX + role.name());
                    roleRepository.save(roleEntity);
                    roles.add(roleEntity);
                }
        );

        userEntity.setUserId("user1");
        userEntity.setPassword(bCryptPasswordEncoder.encode("pass"));
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }
}
