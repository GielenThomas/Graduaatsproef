package com.dnd.charactergenerator.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(0)
    private int strength;
    @Min(0)
    private int dexterity;
    @Min(0)
    private int constitution;
    @Min(0)
    private int intelligence;
    @Min(0)
    private int wisdom;
    @Min(0)
    private int charisma;
}
