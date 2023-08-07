package com.api.gamerating.service;

import com.api.gamerating.entity.Rating;
import com.api.gamerating.repository.RatingDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    RatingDao ratingDao;

    @Autowired
    public RatingServiceImpl(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    @Override
    public Rating findRatingById(int id) {
        return ratingDao.findRatingById(id);
    }

    public List<Rating> findAllRatings(){
        return ratingDao.findAll();

    }


    @Override
    public Rating findRatingByLevel(String level) {
        return ratingDao.findRatingByLevel(level);
    }


}
