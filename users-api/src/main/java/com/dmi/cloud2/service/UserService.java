package com.dmi.cloud2.service;

import com.dmi.cloud2.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);

    UserDto getUserByUserId(String userId);
}
