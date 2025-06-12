package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.Character;
import com.dnd.model.CharacterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterResponse toCharacterResponse(Character character);
}
