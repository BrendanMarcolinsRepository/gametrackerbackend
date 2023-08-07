package com.api.gamerating.service;

import com.api.gamerating.entity.Rating;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    Rating findRatingById(int id);

    Rating findRatingByLevel(String level);
}
