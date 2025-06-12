package com.dnd.charactergenerator.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Character {
    private static final int STARTING_LEVEL = 1;
    private static final int STARTING_XP = 0;

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String race;
    private String characterClass;
    @Builder.Default
    private int level = STARTING_LEVEL;
    private String background;
    private String alignment;
    @Builder.Default
    private int xp = STARTING_XP;
    private Abilities abilities;
    private Skills skills;

    private int armorClass;
    private String speed;

    private int currentHealth;
    private int maxHealth;
    private int hitDie;

    @OneToMany
    private List<Spell> spells;

    @OneToMany
    private List<Feat> feats;



}
