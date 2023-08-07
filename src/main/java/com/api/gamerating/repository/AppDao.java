package com.api.gamerating.repository;

import com.api.gamerating.entity.*;

import java.util.List;

public interface AppDao {

    void save(Game game);

    Game findGameById(int id);

   // void deleteGame(int id);

    Rating findRatingById(int id);

    void deleteRatingById(int id);

   List<Category> findGameByCategoryId(int id);

    Game findGameByCategoryIdJoinFetch(int id);

    void update(Game game);

    void update(Category category);

    Category findCategoryById(int id);


    void deleteCategoryById(int id);

    Game findGameAndReviewsByGameId(int id);

    Game findGameandUsersByGameId(int id);

    User findUserandGameByGameId(int id);

    void update(User user);

    void deleteUserById(int id);

    void addReviewByUser(Review review);

    Rating findReviewById(int id);

    void deleteReviewById(int id);

}
