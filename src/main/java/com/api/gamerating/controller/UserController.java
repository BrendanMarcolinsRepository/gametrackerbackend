package com.api.gamerating.controller;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;
import com.api.gamerating.exceptions.ResourceNotFoundException;
import com.api.gamerating.service.CategoryService;
import com.api.gamerating.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiuser")
public class UserController {

    private CategoryService categoryService;

    @Autowired
    public UserController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


}
