package com.api.gamerating.service;

import com.api.gamerating.entity.Review;
import com.api.gamerating.repository.ReviewRespository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRespository reviewRespository;

    public ReviewServiceImpl(ReviewRespository reviewRespository) {
        this.reviewRespository = reviewRespository;
    }

    @Override
    public Optional<Review> getAllReviews() {

        Optional<Review> optional = reviewRespository
                .findAll().stream().findAny();

        if(optional.isPresent()){return optional;}

        return null;

    }
}
