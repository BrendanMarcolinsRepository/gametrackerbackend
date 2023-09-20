package com.api.gamerating.controller;

import com.api.gamerating.dto.CredentialsDto;
import com.api.gamerating.dto.SignUpDto;
import com.api.gamerating.dto.UserDto;
import com.api.gamerating.security.UserAuthProvider;
import com.api.gamerating.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthenticationProvider;



    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {

        System.out.println("working: " + credentialsDto);

        UserDto userDto = userService.login(credentialsDto);

        System.out.println("working: " + userDto.toString());

        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        System.out.println("User roles " + user.getRoles());


        UserDto createdUser = userService.register(user);

        System.out.println("User roles 2  " + createdUser.toString());

        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));

        System.out.println(createdUser);

        return ResponseEntity.created(URI.create("api/registerUsers" + createdUser.getId())).body(createdUser);
    }

}
