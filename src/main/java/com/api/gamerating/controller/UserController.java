package com.api.gamerating.controller;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;
import com.api.gamerating.service.CategoryService;
import com.api.gamerating.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apiuser")
public class UserController {

    private CategoryService categoryService;

    @Autowired
    public UserController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/game/category/{value}")
    public List<Game> findGameByCategory(@PathVariable String value){
        List<Category> categories = categoryService.findCategoriesByTitleWithGames(value);
        if(categories.isEmpty()){

            return null;
        }

        List<Game> games = new ArrayList<>();
        categories.forEach(n -> games.add(n.getGame()));
        return games;

    }
}
