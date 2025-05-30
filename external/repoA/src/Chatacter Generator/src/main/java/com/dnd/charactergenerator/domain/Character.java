package com.dnd.charactergenerator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
    private Race race;
    private int level;
    private String background;
    private String alignment;
    private int xp;
    private Abilities abilities;
    private Skills skills;

    private int armorClass;
    private int speed;

    private int maxHealth;
    private int currentHealth;

    @OneToMany
    private List<Spell> spells;

    @OneToMany
    private List<Feat> feats;

}
