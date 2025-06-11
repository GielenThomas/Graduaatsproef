package com.dnd.charactergenerator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Abilities {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public int calculateModifier(int score) {
        return (score - 10) / 2;
    }

    public boolean isValidPointBuy() {

            int totalPoints = 0;
            int[] scores = {strength, dexterity, constitution, intelligence, wisdom, charisma};

            for (int score : scores) {
                totalPoints += pointCost(score);
            }

            return totalPoints <= 27;

    }

    private int pointCost(int score) {
        if (score < 8 || score > 15) {
            throw new IllegalArgumentException("Ability score must be between 8 and 15 for point-buy calculation.");
        }

        if (score <= 13) return score - 8;
        else return 5 + 2 * (score - 13);
    }

    public Abilities add(Abilities other) {
        return new Abilities(
                this.strength + other.strength,
                this.dexterity + other.dexterity,
                this.constitution + other.constitution,
                this.intelligence + other.intelligence,
                this.wisdom + other.wisdom,
                this.charisma + other.charisma
        );
    }

}
