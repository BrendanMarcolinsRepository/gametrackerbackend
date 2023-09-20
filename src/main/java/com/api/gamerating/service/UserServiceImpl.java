package com.api.gamerating.service;

import com.api.gamerating.dto.CredentialsDto;

import com.api.gamerating.dto.SignUpDto;
import com.api.gamerating.dto.UserDto;
import com.api.gamerating.entity.User;
import com.api.gamerating.exceptions.AppException;
import com.api.gamerating.mappers.UserMapper;
import com.api.gamerating.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

//implements UserService@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    private final PasswordEncoder passwordEncoder;

     public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
         this.userRepository = userRepository;
         this.userMapper = userMapper;
         this.passwordEncoder = passwordEncoder;
     }


     public UserDto findByLogin(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(
                "Unknown User", HttpStatus.NOT_FOUND
        ));

        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto){
        User user = userRepository.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new AppException("Uknown user",HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),user.getPassword())){

            System.out.println("User  " + user.getUsername() + " get roles" + user.getRoles());

            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUp){
        Optional<User> optionalUsername = userRepository.findByUsername(signUp.getUsername());
        Optional<User> optionalUserEmail = userRepository.findByUsername(signUp.getEmail());

        if(optionalUsername.isPresent() || optionalUserEmail.isPresent()){
            throw new AppException("Username or Email already exist", HttpStatus.BAD_REQUEST);
        }

        System.out.println("\t\t\tChecking user here ---- " + signUp);

        User user = userMapper.signUpToUser(signUp);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUp.getPassword())));

        System.out.println("\t\t\tChecking user here ---- " + user);

        User savedeUser = userRepository.save(user);

        return userMapper.toUserDto(user);

    }
    /*
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername2(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername2(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>
                                                                                 roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }

     */
}
