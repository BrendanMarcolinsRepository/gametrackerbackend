package com.api.gamerating.externalapi;


import com.api.gamerating.dto.GameDto;
import com.api.gamerating.entity.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;


@RestController
public class ExternalApiGamesImpl implements ExternalApiGames {

    private final String KEY = "dce9ebc2cb0647be98bc8919db2c7702";

    public GameDto restTemplate(String slug) {

        slug = slug.toLowerCase().replace(" ","-");

        String url = "https://api.rawg.io/api/games/" + slug + "?key="+KEY;


        String response = "";
        GameDto gameDto = null;


        System.out.println("Continued-----------------------------------> 1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ExternalApiException());
        response = restTemplate.getForObject(url, String.class);

        System.out.println("Continued-----------------------------------> 2" + response);

        //if you want to store the data from the api = but instead we are just checking
        //to see if the game exist at all.


        if(!response.contains("{\"detail\":\"Not found.\"}")){
            System.out.println("Continued-----------------------------------> 3");
            Gson gson = new Gson();
            JsonObject body = gson.fromJson(response, JsonObject.class);
            String title = body.get("name").getAsString();
            String publisher = "Sony Entertainment ";
            String description = body.get("description").getAsString();
            String release_date = body.get("released").getAsString();

            description = description
                    .replace("<p>", "")
                    .replace("</p>", "")
                    .replace("&#39;", "'");

            System.out.println("Char desc === " + description.length());

            System.out.println("date ==== " + release_date + " 00:00:00");

            gameDto = new GameDto(title,publisher,description, Timestamp.valueOf(release_date + " 00:00:00"));



        }
        return gameDto;

        /*
        //retrieve the title of the game from the api
        // and compare it to what the user has inputted
        // from the front-end

        Gson gson = new Gson();
        JsonObject body = gson.fromJson(response, JsonObject.class);
        String title = body.get("name").getAsString();

        return compareFunction(slug, title);

*
         */




    }

    private boolean compareFunction(String slug, String title) {
        return slug.contains(title);
    }

}
