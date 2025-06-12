package com.dnd.charactergenerator.domain;



import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CharacterGenerateInfo {

    String name;
    String raceId;
    String characterClassId;
    String background;
    String alignment;
    Abilities abilities;
    List<UUID> spellIds;
}
