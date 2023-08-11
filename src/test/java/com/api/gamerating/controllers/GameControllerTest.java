package com.api.gamerating.controllers;

import com.api.gamerating.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class GameControllerTest {
    private static MockHttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;


    @Test
    public void getAllGamesTestHttpRequest() throws Exception {

        assertFalse(gameRepository.findAllGames().isEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findGamesByTitleTestHttpRequest() throws Exception {

        final String title = "God Of War";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/games/"+title))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findGamesByTitleThatDoesNotExistTestHttpRequest() throws Exception {

        final String title = "Zero";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/games/"+title))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findGamesByRatingTestHttpRequest() throws Exception {

        final String rating = "R";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/rating/"+rating))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findGamesByRatingThatDoesNotExistTestHttpRequest() throws Exception {

        final String rating = "L";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/rating/"+rating)).andExpect(null);
    }

    @Test
    public void findGamesByDatesOrdered() throws Exception {

        final String order = "latest"; // or oldest
        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/order/date/"+order))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));


    }

    @Test
    public void findGamesByTitledOrdered() throws Exception {

        final String order = "A-Z"; // or oldest
        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/order/title/"+order))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));


    }

    @Test
    public void findGameByCategoryTestHttpRequest() throws Exception {
        final String categoryTest = "Action";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/category/"+categoryTest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

    }


    @Test
    public void findGameByCategoryNonExistentTestHttpRequest() throws Exception {
        final String categoryTest = "Horror";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/category/"+categoryTest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());




    }


    @Test
    public void findReviewByGameTestHttpRequest() throws Exception {
        final String gameReviewTest = "God Of War";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reviews/"+gameReviewTest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));




    }

    @Test
    public void findReviewByGameNonExistentTestHttpRequest() throws Exception {
        final String gameReviewTest = "Returnal";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reviews/"+gameReviewTest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());





    }

}
