package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.Character;
import com.dnd.charactergenerator.domain.CharacterGenerateInfo;
import com.dnd.model.CharacterResponse;
import com.dnd.model.GenerateCharacterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterResponse toCharacterResponse(Character character);
    CharacterGenerateInfo toCharacterGenerateInfo(GenerateCharacterRequest generateCharacterRequest);
}
