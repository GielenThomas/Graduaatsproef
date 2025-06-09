package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.Feat;
import com.dnd.charactergenerator.domain.Race;
import com.dnd.charactergenerator.repository.FeatRepository;
import com.dnd.model.RaceRequest;
import com.dnd.model.RaceResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {FeatMapper.class})
public abstract class RaceMapper {
    private final FeatRepository featRepository;
    protected RaceMapper(FeatRepository featRepository) {
        this.featRepository = featRepository;

    }

    @Mapping(target = "feats", ignore = true)
    public abstract Race toRace(RaceRequest raceRequest);

    @AfterMapping
    protected void mapFeats(RaceRequest request, @MappingTarget Race race) {
        List<UUID> ids = request.getFeats().stream().map(UUID::fromString).toList();
        List<Feat> feats = featRepository.findAllById(ids);
        if (feats.size() != request.getFeats().size()) {
            throw new IllegalArgumentException("Invalid feat IDs.");
        }
        race.setFeats(feats);
    }


    public abstract RaceResponse toRaceResponse(Race race);
}
