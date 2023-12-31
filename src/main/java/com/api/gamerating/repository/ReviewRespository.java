package com.api.gamerating.repository;

import com.api.gamerating.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRespository extends JpaRepository<Review, Long> {


    List<Review> findAll();
}
