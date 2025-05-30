package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.Spell;
import com.dnd.model.SpellRequest;
import com.dnd.model.SpellResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpellMapper {
    Spell toSpell(SpellRequest spellRequest);
    SpellResponse toSpellResponse(Spell spell);
}
