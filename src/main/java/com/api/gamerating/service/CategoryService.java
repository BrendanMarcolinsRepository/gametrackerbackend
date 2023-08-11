package com.api.gamerating.service;

import com.api.gamerating.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findCategoriesByTitleWithGames(String value);
}
