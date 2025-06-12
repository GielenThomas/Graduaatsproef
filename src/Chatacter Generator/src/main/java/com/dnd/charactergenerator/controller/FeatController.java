package com.dnd.charactergenerator.controller;

import com.dnd.api.FeatsApi;
import com.dnd.charactergenerator.domain.Feat;
import com.dnd.charactergenerator.mapper.FeatMapper;
import com.dnd.charactergenerator.service.FeatService;
import com.dnd.model.FeatRequest;
import com.dnd.model.FeatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FeatController implements FeatsApi {

    private final FeatService featService;
    private final FeatMapper featMapper;

    @Autowired
    public FeatController(FeatService featService, FeatMapper featMapper) {
        this.featService = featService;
        this.featMapper = featMapper;
    }
    @Override
    public ResponseEntity<List<FeatResponse>> featsGet() {
        List<FeatResponse> feats = featService.getAllFeats().stream().map(featMapper::toFeatResponse).collect(Collectors.toList());
        return new ResponseEntity<>(feats, HttpStatus.OK);
    }



    @Secured("ROLE_ADMIN")
    @Override
    public ResponseEntity<Void> featsIdDelete(String id) {
       featService.deleteFeat(UUID.fromString(id));
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FeatResponse> featsIdGet(String id) {
        Optional<Feat> feat = featService.getFeatById(UUID.fromString(id));
        return feat.map(e -> new ResponseEntity<>(featMapper.toFeatResponse(e),HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<FeatResponse> featsIdPut(String id, FeatRequest featRequest) {
        Optional<Feat> feat = featService.updateFeat(UUID.fromString(id),featMapper.toFeat(featRequest));
        return feat.map(e -> new ResponseEntity<>(featMapper.toFeatResponse(e),HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> featsPost(FeatRequest featRequest) {
        featService.createFeat(featMapper.toFeat(featRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
