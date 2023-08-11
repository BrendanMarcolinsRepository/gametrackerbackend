package com.api.gamerating.repository;

import com.api.gamerating.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query(value = "SELECT * FROM Category WHERE Title=:title", nativeQuery = true)
    List<Category> findAllByTitle(@Param("title") String title);
}
