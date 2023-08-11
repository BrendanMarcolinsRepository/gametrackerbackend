package com.api.gamerating.service;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Review;
import com.api.gamerating.repository.GameRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAllGames();
    }

    @Override
    @Transactional
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Game findGameById(int id) {
        return gameRepository.findGameById(id);
    }

    @Override
    @Transactional
    public void deleteGame(int id) {

        //gameRepository.deleteGame(id);
    }

    @Override
    public List<Game> findGameByRating(int id) {
        return gameRepository.findGameByRating(id);
    }

    @Override
    public List<Game> findGameByName(String name) {
        if(name.isBlank() || name.isEmpty()){

        }

        return gameRepository.findGameByName(name);
    }

    @Override
    public List<Game> findGamesByDatesOrderedLatest(String value) {

        /* Not useful just some practice - better sorting in
           through the database
        List<Game> games;

        try{
            games = gameRepository.findAllGames();
            games.sort(Comparator.comparing(Game::getReleaseDate));
            return games;
        }catch (Exception e){
            return null;
        }

         */

        return gameRepository.findGamesByDatesOrderedLatest(value);

    }

    @Override
    public List<Game> findGamesByTitleOrderedLatest(String value) {
        return gameRepository.findGamesByTitleOrderedLatest(value);
    }

    public List<Review> findReviewByGame(String value) {
        List<Review> reviews = gameRepository.findGameByName(value)
                .stream()
                .filter(game -> game.getTitle().contains(value))
                .flatMap(review -> review.getReviews().stream())
                .toList();

        if(reviews.isEmpty()) {return null;}

        return reviews;
    }

    @Override
    public List<Game> findGameByCategory(List<Integer> value) {
        return gameRepository.findGameByCategory(value);
    }
}
