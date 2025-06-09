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
        race.setId(id);
        raceRepository.save(race);
        return Optional.of(race);
    }
}
