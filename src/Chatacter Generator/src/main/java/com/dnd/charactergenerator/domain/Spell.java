package com.dnd.charactergenerator.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Spell {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String level;
    @Enumerated(EnumType.STRING)
    private School school;
    private String castingTime;
    private String spellRange;
    private String duration;
    private String description;
    @ElementCollection(targetClass = Component.class)
    @Enumerated(EnumType.STRING)
    private List<Component> components;
    private String higherLevels;




    public enum School {
        ABJURATION,
        CONJURATION,
        DIVINATION,
        ENCHANTMENT,
        EVOCATION,
        ILLUSION,
        NECROMANCY,
        TRANSMUTATION,

    }

    public enum Component{
        VERBAL,
        SOMATIC,
        MATERIAL
    }


}


