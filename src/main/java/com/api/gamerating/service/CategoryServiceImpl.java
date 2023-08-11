package com.api.gamerating.service;

import com.api.gamerating.entity.Category;
import com.api.gamerating.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findCategoriesByTitleWithGames(String value) {
        return categoryRepository.findAllByTitle(value);
    }
}
