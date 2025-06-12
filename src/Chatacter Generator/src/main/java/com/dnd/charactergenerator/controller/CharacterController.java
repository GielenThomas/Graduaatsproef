package com.dnd.charactergenerator.controller;

import com.dnd.api.CharactersApi;
import com.dnd.charactergenerator.domain.Character;
import com.dnd.charactergenerator.mapper.CharacterMapper;
import com.dnd.charactergenerator.service.CharacterService;
import com.dnd.model.CharacterResponse;
import com.dnd.model.GenerateCharacterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CharacterController implements CharactersApi {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }
    @Override
    @Secured("ROLE_USER")
    public ResponseEntity<Void> charactersGeneratePost(GenerateCharacterRequest generateCharacterRequest) {
        characterService.generateCharacter(characterMapper.toCharacterGenerateInfo(generateCharacterRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Override
    @Secured("ROLE_USER")
    public ResponseEntity<List<CharacterResponse>> charactersGet() {
        List<CharacterResponse> characters = characterService.getCharacters().stream().map(characterMapper::toCharacterResponse).toList();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @Override
    @Secured("ROLE_USER")
    public ResponseEntity<CharacterResponse> charactersIdGet(String id) {
        Character character = characterService.getCharacter(UUID.fromString(id));
        return new ResponseEntity<>(characterMapper.toCharacterResponse(character), HttpStatus.OK);
    }
}
