package com.api.gamerating.service;

import com.api.gamerating.dto.CredentialsDto;
import com.api.gamerating.dto.SignUpDto;
import com.api.gamerating.dto.UserDto;
import com.api.gamerating.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Optional;
//extends UserDetailsService
public interface UserService {
    ///public User findByUserName(String userName);

    UserDto findByLogin(String login);


    UserDto login(CredentialsDto credentialsDto);

    UserDto register(SignUpDto signUp);

}
