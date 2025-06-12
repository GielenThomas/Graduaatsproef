package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.auth.AuthUtil;
import com.dnd.charactergenerator.domain.Character;
import com.dnd.charactergenerator.domain.User;
import com.dnd.charactergenerator.repository.CharacterRepository;
import com.dnd.charactergenerator.repository.UserRepository;
import com.dnd.model.GenerateCharacterRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {
    private final AuthUtil authUtil;
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;

    public CharacterService(AuthUtil authUtil, CharacterRepository characterRepository, UserRepository userRepository) {
        this.authUtil = authUtil;
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
    }

    public List<Character> getCharacters() {
        User user = authUtil.getCurrentUser();
        return user.getCharacters();
    }

    public Character getCharacter(UUID characterId) {
        User user = authUtil.getCurrentUser();

        return characterRepository.findById(characterId)
                .filter(character -> user.getCharacters().contains(character))
                .orElseThrow(() -> new AccessDeniedException("Character not found or does not belong to user"));
    }

    public void generateCharacter(GenerateCharacterRequest generateCharacterRequest) {
        Character character = Character.builder().build();
        User user = authUtil.getCurrentUser();
        user.addCharacter(character);
        userRepository.save(user);
    }


}
