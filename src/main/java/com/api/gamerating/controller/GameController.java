package com.api.gamerating.controller;

import com.api.gamerating.dto.GameDto;
import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Rating;
import com.api.gamerating.entity.Review;
import com.api.gamerating.service.CategoryService;
import com.api.gamerating.service.GameService;
import com.api.gamerating.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private GameService gameService;
    private RatingService ratingService;

    private CategoryService categoryService;


    @Autowired
    public GameController(GameService gameService, RatingService ratingService, CategoryService categoryService) {
        this.gameService = gameService;
        this.ratingService = ratingService;
        this.categoryService = categoryService;
    }


    @GetMapping("/games")
    public List<Game> findAllGames(){
        return gameService.findAllGames();
    }


    @PostMapping("/")
    public void save(@RequestBody Game game) {
        gameService.save(game);
    }


    @GetMapping("/game/{gameId}")
    public Game findGameById(@PathVariable("gameId") int gameId) {
        return gameService.findGameById(gameId);
    }

    @GetMapping("/games/{name}")
    public List<Game> findGameByName(@PathVariable("name") String name) {
        return gameService.findGameByName(name);
    }


    @GetMapping("/game/rating/{level}")
    public List<Game> findGamesWithASpecificRating(@PathVariable String level){

        System.out.println("Here " + level);
        Rating rating;

        try{
           rating = ratingService.findRatingByLevel(level);
           return gameService.findGameByRating(rating.getId());

        }catch (Exception e){

        }finally {
            System.out.println("finished finding rating");
        }


        return null;


    }

    @GetMapping("/game/order/date/{value}")
    public List<Game> findGamesByDatesOrdered(@PathVariable String value){
        return gameService.findGamesByDatesOrderedLatest(value);

    }

    @GetMapping("/game/order/title/{value}")
    public List<Game> findGamesByTitleOrdered(@PathVariable String value){
        return gameService.findGamesByTitleOrderedLatest(value);

    }

    @GetMapping("/game/category/{value}")
    public List<Object> findGameByCategory(@PathVariable String value){

        if(categoryService.findCategoriesByTitleWithGames(value).get().isEmpty()){

            return Collections.singletonList(HttpStatus.NO_CONTENT);
        }

        return Collections.singletonList(categoryService.findCategoriesByTitleWithGames(value).get());
    }

    @GetMapping("/reviews/{value}")
    public List<Review> findReviewByGame(@PathVariable String value){

       return gameService.findReviewByGame(value);

    }

    @PostMapping("/game")
    public String addNonExisitingGame(@RequestBody @Valid GameDto tempGame){
        return gameService.addNonExistingGame(tempGame);
    }



    @DeleteMapping("/game/{gameId}")
    public void deleteGame(int id) {
        gameService.deleteGame(id);

    }


}
