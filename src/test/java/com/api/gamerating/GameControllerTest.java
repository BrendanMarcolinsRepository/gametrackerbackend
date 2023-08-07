package com.api.gamerating;

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
import static org.mockito.Mockito.when;
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



}
