package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.domain.CharacterClass;
import com.dnd.charactergenerator.repository.CharacterClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterClassService {

    private final CharacterClassRepository characterClassRepository;
    public CharacterClassService(CharacterClassRepository characterClassRepository) {
        this.characterClassRepository = characterClassRepository;
    }

    public List<CharacterClass> getAllClasses() {
        return characterClassRepository.findAll();
    }

    public Optional<CharacterClass> getClassById(UUID id) {
        return characterClassRepository.findById(id);
    }

    public void createClass(CharacterClass characterClass) {
        characterClassRepository.save(characterClass);
    }

    public void deleteClass(UUID id) {
        characterClassRepository.deleteById(id);
    }

    public Optional<CharacterClass> updateClass(UUID id, CharacterClass characterClass) {
        characterClass.setId(id);
        characterClassRepository.save(characterClass);
        return Optional.of(characterClass);
    }
}
