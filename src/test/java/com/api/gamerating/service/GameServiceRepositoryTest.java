package com.api.gamerating.service;

import com.api.gamerating.dto.GameDto;
import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.Rating;
import com.api.gamerating.entity.Review;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import static org.junit.Assert.assertThat;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class GameServiceRepositoryTest {



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GameService gameService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CategoryService categoryService;

    @Value("${sql.script.create.game}")
    private String sqlAddGame;

    @Value("${sql.script.delete.game}")
    private String sqlDeleteGame;

    @BeforeEach
    public void setUpDatebase(){
        jdbcTemplate.execute(sqlAddGame);
    }

    @Test
    public void isGameExistentChecks(){
        assertNotNull(gameService.findGameById(10));
        assertNull(gameService.findGameById(11));
    }

    @Test
    public void findAllGamesTest(){

        //When not empty
        List<Game> gameList = gameService.findAllGames();

        assertFalse(gameList.isEmpty());

        //When empty
        List<Game> empty = new ArrayList<>();
        assertEquals(List.of(),empty);
        assertEquals(0,empty.size());



        Game game1 = new Game("God Of War", "Sony Entertainment","Platformer", Timestamp.valueOf("2021-03-21 00:00:00"));
        Game game2 = new Game("Spider-Man", "Sony Entertainment","Platformer",Timestamp.valueOf("2019-03-21 00:00:00"));
        Game game3 = new Game("Ghost Of Tsushima","Sony Entertainment","Action",Timestamp.valueOf("2019-03-21 00:00:00"));

        List<Game> testGames = new ArrayList<>();
        testGames.add(game1);
        testGames.add(game2);
        testGames.add(game3);

        assertEquals(testGames.size(),gameList.size());

        for(int i = 0; i < gameList.size(); i++){
            assertEquals(gameList.get(i).getTitle(), testGames.get(i).getTitle());
            assertEquals(gameList.get(i).getDescription(), testGames.get(i).getDescription());
            assertEquals(gameList.get(i).getPublisher(), testGames.get(i).getPublisher());
            assertEquals(gameList.get(i).getReleaseDate(), testGames.get(i).getReleaseDate());
        }







    }

    @Test
    public void findGameIfNotExist() {

        Game gameFromDatabase = gameService.findGameById(10);
        GameDto game = new GameDto(
                gameFromDatabase.getTitle(),
                gameFromDatabase.getPublisher(),
                gameFromDatabase.getDescription(),
                gameFromDatabase.getReleaseDate());

        assertNotNull(game, "Game Exist!!!");

        assertEquals("Game Already Exist", gameService.addNonExistingGame(game));

        game = new GameDto("Chad","Sony Entertainment","Platformer", new Timestamp(2021-03-21));
        assertEquals("No Game to save", gameService.addNonExistingGame(game));

        game = new GameDto("The Witcher 3: Wild Hunt","Sony Entertainment","Platformer", new Timestamp(2021-03-21));
        assertEquals("No Game to save", gameService.addNonExistingGame(game));



    }


    @Test
    public void findGameByRatingTest(){

        String sampleUserInput = "R";

        //When games exist with a rating of "R"
        List<Game> games = gameService.findGameByRating(1);
        assertNotNull(games);
        assertFalse(games.isEmpty());
        assertEquals(games.size(),2);

        //When games don't exist with a rating of "G"
        games = gameService.findGameByRating(5);
        assertEquals(List.of(),games);
        assertTrue(games.isEmpty());
        assertEquals(games.size(),0);


    }

    @Test
    public void findGameByCategory(){

        List<Object> games = Collections.singletonList(categoryService.findCategoriesByTitleWithGames("Action").get());
        assertNotNull(games);
        assertFalse(games.isEmpty());
        assertEquals(games.size(),1);

    }



    @Test
    public void findReviewByGameExist(){
        List<Review> reviews = gameService.findReviewByGame("God Of War");
        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
        assertEquals(reviews.size(), 1);





    }

    @Test
    public void findReviewByGameDoesntExist(){
        List<Review> reviews = gameService.findReviewByGame("God of No War");
        assertNull(reviews);



    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbcTemplate.execute(sqlDeleteGame);

    }

}
