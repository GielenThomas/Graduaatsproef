package com.dnd.charactergenerator.controller;

import com.dnd.api.ClassesApi;
import com.dnd.charactergenerator.domain.CharacterClass;
import com.dnd.charactergenerator.mapper.ClassMapper;
import com.dnd.charactergenerator.service.CharacterClassService;
import com.dnd.model.CharacterClassRequest;
import com.dnd.model.CharacterClassResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ClassController implements ClassesApi {
    private final CharacterClassService classService;
    private final ClassMapper classMapper;
    public ClassController(CharacterClassService classService, ClassMapper classMapper) {
        this.classService = classService;
        this.classMapper = classMapper;
    }

    @Override
    public ResponseEntity<List<CharacterClassResponse>> classesGet() {
        List<CharacterClassResponse> classes = classService.getAllClasses().stream().map(classMapper::toCharacterClassResponse).toList();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> classesIdDelete(String id) {
        classService.deleteClass(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CharacterClassResponse> classesIdGet(String id) {
        Optional<CharacterClass> feat = classService.getClassById(UUID.fromString(id));
        return feat.map(e -> new ResponseEntity<>(classMapper.toCharacterClassResponse(e),HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CharacterClassResponse> classesIdPut(String id, CharacterClassRequest characterClassRequest) {
        Optional<CharacterClass> characterClass = classService.updateClass(UUID.fromString(id),classMapper.toCharacterClass(characterClassRequest));
        return characterClass.map(e -> new ResponseEntity<>(classMapper.toCharacterClassResponse(e),HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Object> classesPost(CharacterClassRequest characterClassRequest) {
        classService.createClass(classMapper.toCharacterClass(characterClassRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
