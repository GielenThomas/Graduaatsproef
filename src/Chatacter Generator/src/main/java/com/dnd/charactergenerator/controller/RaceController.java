package com.dnd.charactergenerator.controller;

import com.dnd.api.RacesApi;
import com.dnd.charactergenerator.domain.Race;
import com.dnd.charactergenerator.mapper.RaceMapper;
import com.dnd.charactergenerator.service.RaceService;
import com.dnd.model.RaceRequest;
import com.dnd.model.RaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class RaceController implements RacesApi {
    private final RaceService raceService;
    private final RaceMapper raceMapper;
    @Autowired
    public RaceController(RaceService raceService, RaceMapper raceMapper) {
        this.raceService = raceService;
        this.raceMapper = raceMapper;
    }

    @Override
    public ResponseEntity<List<RaceResponse>> racesGet() {
        List<RaceResponse> races = raceService.getAllRaces().stream().map(raceMapper::toraceResponse).toList();
        return new ResponseEntity<>(races, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> racesIdDelete(String id) {
        raceService.deleteRace(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RaceResponse> racesIdGet(String id) {
        Optional<Race> race = raceService.getRaceById(UUID.fromString(id));
        return race.map(e -> new ResponseEntity<>(raceMapper.toraceResponse(e), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> racesPost(RaceRequest raceRequest) {
        raceService.createRace(raceMapper.toRace(raceRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
