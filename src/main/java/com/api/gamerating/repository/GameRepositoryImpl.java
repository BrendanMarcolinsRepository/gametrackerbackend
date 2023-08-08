package com.api.gamerating.repository;

import com.api.gamerating.entity.Category;
import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Rating;
import com.api.gamerating.util.repository.UtilRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Repository
public class GameRepositoryImpl implements  GameRepository{

    private EntityManager entityManager;

    @Autowired
    public GameRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Game> findAllGames() {



        TypedQuery<Game> query = entityManager.createQuery(
                "from Game", Game.class);

        List<Game> games = query.getResultList();

        System.out.println("Game is here " + games);

        return games;
    }

    @Override
    @Transactional
    public void save(Game game) {
        entityManager.persist(game);
    }

    @Override
    public Game findGameById(int id) {

        System.out.println("Here " + id);

        Game game = entityManager.find(Game.class, id);

        System.out.println("Here = " + game);

        return game;
    }

    public List<Game> findGameByRating(int id){

        TypedQuery<Game> query = entityManager.createQuery(
                "from Game where rating.id = :data", Game.class);
        query.setParameter("data", id);

        return query.getResultList();

    }

    @Override
    public List<Game> findGameByName(String name) {
        TypedQuery<Game> query = entityManager.createQuery(
                "from Game where title = :data", Game.class);
        query.setParameter("data", name);

        return query.getResultList();

    }

    @Override
    public List<Game> findGamesByDatesOrderedLatest(String value) {

        String order = new UtilRepository().orderingForDatabase(value, "oldest","latest");
        /*
            value will be a parameter passed from
            the front end via a drop list, hence the string.
         */

        TypedQuery<Game> query  = entityManager.createQuery(
                "from Game order by releaseDate " + order
                , Game.class);


        return query.getResultList();
    }

    @Override
    public List<Game> findGamesByTitleOrderedLatest(String value) {


        String order = new UtilRepository().orderingForDatabase(value, "A-Z","Z-A");

        TypedQuery<Game> query  = entityManager.createQuery(
                "from Game order by title " + order
                , Game.class);


        return query.getResultList();
    }
    /*
    @Override
    @Transactional
    public void deleteGame(int id) {

        Game game = entityManager.find(Game.class, id);

        List<Category> categories = game.getCategories();

        for(Category category : categories){
            category.setGame(null);
        }

        entityManager.remove(game);
    }

     */
}
