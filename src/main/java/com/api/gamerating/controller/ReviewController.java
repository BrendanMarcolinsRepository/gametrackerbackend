package com.api.gamerating.controller;

import com.api.gamerating.entity.Review;
import com.api.gamerating.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public Optional<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }


}
