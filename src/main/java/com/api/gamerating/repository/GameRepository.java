package com.api.gamerating.repository;

import com.api.gamerating.entity.Game;

import java.util.List;

public interface GameRepository {

    List<Game> findAllGames();

    void save(Game game);

    Game findGameById(int id);

   // void deleteGame(int id);

    List<Game> findGameByRating(int id);

    List<Game> findGameByName(String name);

    List<Game> findGamesByDatesOrderedLatest(String value);

    List<Game> findGamesByTitleOrderedLatest(String value);

    List<Game> findGameByCategory(List<Integer> value);
}
