package com.api.gamerating.service;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;
import com.api.gamerating.exceptions.ResourceNotFoundException;
import com.api.gamerating.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<List<Game>> findCategoriesByTitleWithGames(String value) {



        Optional<List<Category>> categories =
                Optional.of(categoryRepository.findAllByTitle(value));




        Optional<List<Game>> games = Optional.of(categories.get()
                .stream()
                .map(Category::getGame)
                .toList());


        return games;
    }
}
