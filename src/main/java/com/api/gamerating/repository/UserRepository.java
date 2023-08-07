package com.api.gamerating.repository;

import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Rating;
import com.api.gamerating.entity.Review;
import com.api.gamerating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {



    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String login);



    boolean existsByUsername(String username);


}
