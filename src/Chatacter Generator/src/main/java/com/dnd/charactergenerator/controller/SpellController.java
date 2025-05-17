package com.dnd.charactergenerator.controller;

import com.dnd.api.SpellsApi;
import com.dnd.charactergenerator.domain.Spell;
import com.dnd.charactergenerator.mapper.SpellMapper;
import com.dnd.charactergenerator.service.SpellService;
import com.dnd.model.SpellRequest;
import com.dnd.model.SpellResponse;
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
public class SpellController implements SpellsApi {

    private final SpellService spellService;
    private final SpellMapper spellMapper;
    @Autowired
    public SpellController(SpellService spellService, SpellMapper spellMapper) {
        this.spellService = spellService;
        this.spellMapper = spellMapper;
    }
    @Override
    public ResponseEntity<List<SpellResponse>> spellsGet() {
        List<SpellResponse> spells = spellService.getAllSpells().stream().map(spellMapper::toSpellResponse).collect(Collectors.toList());
        return new ResponseEntity<>(spells, HttpStatus.OK);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> spellsPost(SpellRequest spellRequest) {
        spellService.CreateSpell(spellMapper.toSpell(spellRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<SpellResponse> spellsIdPut(String id, SpellRequest spellRequest) {
        Optional<Spell> spell = spellService.updateSpell(UUID.fromString(id), spellMapper.toSpell(spellRequest));
        return spell.map(e -> new ResponseEntity<>(spellMapper.toSpellResponse(e),HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> spellsIdDelete(String id) {
        spellService.deleteSpell(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SpellResponse> spellsIdGet(String id) {
        Optional<Spell> spell = spellService.getSpell(UUID.fromString(id));
        return spell.map(e ->new ResponseEntity<>(spellMapper.toSpellResponse(e), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
