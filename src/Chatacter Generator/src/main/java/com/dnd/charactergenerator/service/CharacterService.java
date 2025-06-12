package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.auth.AuthUtil;
import com.dnd.charactergenerator.domain.*;
import com.dnd.charactergenerator.domain.Character;
import com.dnd.charactergenerator.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.dnd.charactergenerator.domain.Abilities.calculateModifier;


@Slf4j
@Service
public class CharacterService {
    private final AuthUtil authUtil;
    private final CharacterRepository characterRepository;
    private final RaceRepository raceRepository;
    private final CharacterClassRepository characterClassRepository;
    private final SpellRepository spellRepository;

    public CharacterService(AuthUtil authUtil, CharacterRepository characterRepository, RaceRepository raceRepository, CharacterClassRepository characterClassRepository, SpellRepository spellRepository) {
        this.authUtil = authUtil;
        this.characterRepository = characterRepository;
        this.raceRepository = raceRepository;
        this.characterClassRepository = characterClassRepository;
        this.spellRepository = spellRepository;
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

    public void deleteCharacter(UUID characterId) {
        User user = authUtil.getCurrentUser();
        Character character = getCharacter(characterId);
        if (!character.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this character");
        }
        characterRepository.deleteById(characterId);
    }

    public void generateCharacter(CharacterGenerateInfo characterGenerateInfo) {

        Race race = getRace(characterGenerateInfo.getRaceId());
        CharacterClass characterClass = getCharacterClass(characterGenerateInfo.getCharacterClassId());
        List<Spell> spells = getSpells(characterGenerateInfo.getSpellIds());

        Abilities abilities = characterGenerateInfo.getAbilities();
        abilities.isValidPointBuy();
        abilities = abilities.add(race.getAbilityScoreIncreases());

        int health = calculateMaxHealth(characterClass.getHitDie(), abilities.getConstitution());
        User user = authUtil.getCurrentUser();

        Character character = Character.builder()
                .name(characterGenerateInfo.getName())
                .race(race.getName())
                .characterClass(characterClass.getName())
                .background(characterGenerateInfo.getBackground())
                .alignment(characterGenerateInfo.getAlignment())
                .abilities(abilities)
                .skills(Skills.calculateSkills(abilities))
                .armorClass(calculateArmorClass(abilities.getDexterity()))
                .speed(race.getSpeed())
                .maxHealth(health)
                .currentHealth(health)
                .hitDie(characterClass.getHitDie())
                .spells(spells)
                .feats(new ArrayList<>(race.getFeats()))
                .user(user)
                .build();
        characterRepository.save(character);
    }

    private Race getRace(String raceId) {
        return raceRepository.findById(UUID.fromString(raceId)).orElseThrow(() -> new IllegalArgumentException("race not found"));
    }

    private CharacterClass getCharacterClass(String characterClass) {
        return characterClassRepository.findById(UUID.fromString(characterClass)).orElseThrow(() -> new IllegalArgumentException("Class not found"));

    }

    private List<Spell> getSpells(List<UUID> spellIds) {
        return spellRepository.findAllById(spellIds);

    }

    private int calculateArmorClass(int dexterityScore) {
        return 10 + calculateModifier(dexterityScore);
    }

    private int calculateMaxHealth(int hitDie, int constitutionScore) {
        return hitDie + calculateModifier(constitutionScore);
    }


}
