package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.Race;
import com.dnd.model.RaceRequest;
import com.dnd.model.RaceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    Race toRace(RaceRequest raceRequest);
    RaceResponse toraceResponse(Race race);
}
