package com.api.gamerating.controller;

import com.api.gamerating.entity.Rating;
import com.api.gamerating.service.GameServiceImpl;
import com.api.gamerating.service.RatingServiceImpl;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    private RatingServiceImpl ratingService;

    @Autowired
    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }


    @GetMapping("/ratings")
    public List<Rating> findGameRatings(){
        return ratingService.findAllRatings();

    }

    @GetMapping("/rating/{rating}")
    public Rating findGameRatingById(@PathVariable String rating){
        return ratingService.findRatingByLevel(rating);

    }




}
