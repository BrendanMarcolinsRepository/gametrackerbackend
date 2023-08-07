package com.api.gamerating.repository;

import com.api.gamerating.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImplementation implements AppDao{

    private EntityManager entityManager;

    @Autowired
    public AppDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Game game) {
        entityManager.persist(game);
    }

    @Override
    public Game findGameById(int id) {
        return entityManager.find(Game.class, id);
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

    @Override
    public Rating findRatingById(int id) {
        return entityManager.find(Rating.class, id);
    }

    @Override
    @Transactional
    public void deleteRatingById(int id) {
        Rating rating = entityManager.find(Rating.class, id);

        //remove the associated link
        rating.getGame().setRating(null);

        entityManager.remove(rating);
    }

    @Override
    public List<Category> findGameByCategoryId(int id){
        TypedQuery<Category> query = entityManager.createQuery(
                "from Category where game.id = :data", Category.class
        );

        query.setParameter("data",id);

        return query.getResultList();

    }

    @Override
    public Game findGameByCategoryIdJoinFetch(int id) {
        TypedQuery<Game> query = entityManager.createQuery(
                "select g from Game g "
                        + "JOIN FETCH g.categories "
                        + "where g.id = :data",
                        Game.class);
        query.setParameter("data", id);

        Game game = query.getSingleResult();

        return game;
    }

    @Override
    @Transactional
    public void update(Game game) {
        entityManager.merge(game);
    }

    @Override
    @Transactional
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    public Category findCategoryById(int id) {
        return entityManager.find(Category.class, id);

    }

    @Override
    @Transactional
    public void deleteCategoryById(int id) {

        Category category = entityManager.find(Category.class, id);

        entityManager.remove(category);
    }

    @Override
    public Game findGameAndReviewsByGameId(int id) {

        TypedQuery<Game> query = entityManager.createQuery(
                "select g from Game g "
                + "JOIN FETCH g.reviews "
                + "where g.id = :data", Game.class);

        query.setParameter("data", id);

        return query.getSingleResult();

    }

    @Override
    public Game findGameandUsersByGameId(int id) {
        TypedQuery<Game> query = entityManager.createQuery(
                "select g from Game g "
                        + "JOIN FETCH g.users "
                        + "where g.id = :data", Game.class);

        query.setParameter("data", id);

        return query.getSingleResult();

    }

    @Override
    public User findUserandGameByGameId(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u "
                        + "JOIN FETCH u.games "
                        + "where u.id = :data", User.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void addReviewByUser(Review review) {
        entityManager.merge(review);

    }

    @Override
    public Rating findReviewById(int id) {
        TypedQuery<Rating> query = entityManager.createQuery(
                "select r from Rating r "
                        + "JOIN FETCH r.user "
                        + "where r.id = :data", Rating.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteReviewById(int id) {
        entityManager.remove(entityManager.find(Review.class, id));

    }


}
