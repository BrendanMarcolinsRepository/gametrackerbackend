package com.api.gamerating.service;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;
import com.api.gamerating.repository.GameRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
