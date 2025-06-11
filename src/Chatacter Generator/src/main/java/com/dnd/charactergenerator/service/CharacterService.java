package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.auth.AuthUtil;
import com.dnd.charactergenerator.domain.Character;
import com.dnd.charactergenerator.domain.User;
import com.dnd.charactergenerator.repository.CharacterRepository;
import com.dnd.charactergenerator.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;

    public CharacterService(AuthUtil authUtil, UserRepository userRepository, CharacterRepository characterRepository) {
        this.authUtil = authUtil;
        this.userRepository = userRepository;
        this.characterRepository = characterRepository;
    }

    public List<Character> getCharacters() {
        String username = authUtil.getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getCharacters();
    }

    public Character getCharacter(UUID characterId) {
        String username = authUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return characterRepository.findById(characterId)
                .filter(character -> user.getCharacters().contains(character))
                .orElseThrow(() -> new AccessDeniedException("Character not found or does not belong to user"));
    }
}
