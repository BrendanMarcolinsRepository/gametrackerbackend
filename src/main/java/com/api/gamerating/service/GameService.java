package com.api.gamerating.service;

import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Review;

import java.util.List;

public interface GameService {

    List<Game> findAllGames();
    void save(Game game);

    Game findGameById(int id);

    void deleteGame(int id);

    List<Game> findGameByRating(int id);

    List<Game> findGameByName(String name);

    List<Game> findGamesByDatesOrderedLatest(String latest);

    List<Game> findGamesByTitleOrderedLatest(String value);

    List<Game> findGameByCategory(List<Integer> value);

    List<Review> findReviewByGame(String value);
}
