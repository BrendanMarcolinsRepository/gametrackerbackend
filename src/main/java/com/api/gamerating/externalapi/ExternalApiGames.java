package com.api.gamerating.externalapi;

import com.api.gamerating.dto.GameDto;

public interface ExternalApiGames {

    GameDto restTemplate(String slug);
}
