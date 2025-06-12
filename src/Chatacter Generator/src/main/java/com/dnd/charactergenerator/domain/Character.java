package com.dnd.charactergenerator.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`Character`")
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

    @ManyToMany
    @JoinTable(
            name = "character_spell",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private List<Spell> spells;

    @ManyToMany
    @JoinTable(
            name = "character_feat",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "feat_id")
    )
    private List<Feat> feats;
    @ManyToOne @JoinColumn(name="user_id")
    User user;




}
