package com.dnd.charactergenerator.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Skills {
    private int acrobatics = 0;
    private int animalHandling = 0;
    private int arcana = 0;
    private int athletics = 0;
    private int deception = 0;
    private int history = 0;
    private int insight = 0;
    private int intimidation = 0;
    private int investigation = 0;
    private int medicine = 0;
    private int nature = 0;
    private int perception = 0;
    private int performance = 0;
    private int persuasion = 0;
    private int religion = 0;
    private int sleightOfHand = 0;
    private int stealth = 0;
    private int survival = 0;

}
