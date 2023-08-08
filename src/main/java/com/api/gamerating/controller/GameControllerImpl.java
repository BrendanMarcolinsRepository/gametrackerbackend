package com.api.gamerating.controller;

import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Rating;
import com.api.gamerating.service.GameService;
import com.api.gamerating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameControllerImpl {

    private GameService gameService;
    private RatingService ratingService;



    @Autowired
    public GameControllerImpl(GameService gameService, RatingService ratingService) {
        this.gameService = gameService;
        this.ratingService = ratingService;
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

    @DeleteMapping("/game/{gameId}")
    public void deleteGame(int id) {
        gameService.deleteGame(id);

    }


}
