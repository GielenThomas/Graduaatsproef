package com.dnd.charactergenerator.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dnd.charactergenerator.domain.Abilities.calculateModifier;

@Embeddable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Skills {
    private int acrobatics;
    private int animalHandling;
    private int arcana;
    private int athletics;
    private int deception;
    private int history;
    private int insight;
    private int intimidation;
    private int investigation;
    private int medicine;
    private int nature;
    private int perception;
    private int performance;
    private int persuasion;
    private int religion;
    private int sleightOfHand;
    private int stealth;
    private int survival;

    public static Skills calculateSkills(Abilities abilities) {
        return Skills.builder()
                .acrobatics(calculateModifier(abilities.getDexterity()))
                .animalHandling(calculateModifier(abilities.getWisdom()))
                .arcana(calculateModifier(abilities.getIntelligence()))
                .athletics(calculateModifier(abilities.getStrength()))
                .deception(calculateModifier(abilities.getCharisma()))
                .history(calculateModifier(abilities.getIntelligence()))
                .insight(calculateModifier(abilities.getWisdom()))
                .intimidation(calculateModifier(abilities.getCharisma()))
                .investigation(calculateModifier(abilities.getIntelligence()))
                .medicine(calculateModifier(abilities.getWisdom()))
                .nature(calculateModifier(abilities.getIntelligence()))
                .perception(calculateModifier(abilities.getWisdom()))
                .performance(calculateModifier(abilities.getCharisma()))
                .persuasion(calculateModifier(abilities.getCharisma()))
                .religion(calculateModifier(abilities.getIntelligence()))
                .sleightOfHand(calculateModifier(abilities.getDexterity()))
                .stealth(calculateModifier(abilities.getDexterity()))
                .survival(calculateModifier(abilities.getWisdom()))
                .build();
    }



}
