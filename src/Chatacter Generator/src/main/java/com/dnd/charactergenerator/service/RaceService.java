package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.domain.Race;
import com.dnd.charactergenerator.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RaceService {
    private final RaceRepository raceRepository;
    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }
    public Optional<Race> getRaceById(UUID id) {
        return raceRepository.findById(id);
    }

    public void createRace(Race race) {
        raceRepository.save(race);
    }

    public void deleteRace(UUID id) {
        raceRepository.deleteById(id);
    }

    public Optional<Race> updateRace(UUID id, Race race) {
        return raceRepository.findById(id).map(existingRace -> {
            Race updatedRace = existingRace.toBuilder()
                    .id(id)
                    .name(race.getName() != null ? race.getName() : existingRace.getName())
                    .description(race.getDescription() != null ? race.getDescription() : existingRace.getDescription())
                    .abilityScoreIncreases(race.getAbilityScoreIncreases() != null ? race.getAbilityScoreIncreases() : existingRace.getAbilityScoreIncreases())
                    .age(race.getAge() != null ? race.getAge() : existingRace.getAge())
                    .alignment(race.getAlignment() != null ? race.getAlignment() : existingRace.getAlignment())
                    .size(race.getSize() != null ? race.getSize() : existingRace.getSize())
                    .speed(race.getSpeed() != null ? race.getSpeed() : existingRace.getSpeed())
                    .feats(race.getFeats() != null ? race.getFeats() : existingRace.getFeats())
                    .languages(race.getLanguages() != null ? race.getLanguages() : existingRace.getLanguages())
                    .build();
            return raceRepository.save(updatedRace);
        });
    }
}
