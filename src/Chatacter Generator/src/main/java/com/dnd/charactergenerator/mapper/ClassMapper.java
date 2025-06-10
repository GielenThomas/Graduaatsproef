package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.CharacterClass;
import com.dnd.model.CharacterClassRequest;
import com.dnd.model.CharacterClassResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    CharacterClass toCharacterClass(CharacterClassRequest characterClassRequest);
    CharacterClassResponse toCharacterClassResponse(CharacterClass characterClass);
}
