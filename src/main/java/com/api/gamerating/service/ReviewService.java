package com.api.gamerating.service;

import com.api.gamerating.entity.Review;

import java.util.Optional;

public interface ReviewService {
    Optional<Review> getAllReviews();
}
