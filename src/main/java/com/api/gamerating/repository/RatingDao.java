package com.api.gamerating.repository;

import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Rating;
import com.api.gamerating.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingDao extends JpaRepository<Rating, Long> {


    Rating findRatingById(int id);

    List<Rating> findAll();

    Rating findRatingByLevel(String level);
}
