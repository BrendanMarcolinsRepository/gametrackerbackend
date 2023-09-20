package com.api.gamerating.service;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<List<Game>> findCategoriesByTitleWithGames(String value);
}
