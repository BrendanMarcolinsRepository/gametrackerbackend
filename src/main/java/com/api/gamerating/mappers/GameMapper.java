package com.api.gamerating.mappers;

import com.api.gamerating.dto.GameDto;
import com.api.gamerating.entity.Game;
import com.api.gamerating.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GameDto.class})
public interface GameMapper {

    Game saveGame(GameDto gameDto);


}

