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
public class Race {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @Embedded
    private Abilities abilityScoreIncreases;
    private String age;
    private String alignment;
    private String size;
    private String speed;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "race_feats",
            joinColumns = @JoinColumn(name= "race_id"),
            inverseJoinColumns = @JoinColumn(name = "feat_id")
    )
    private List<Feat> feats;
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "race_languages", joinColumns = @JoinColumn(name = "race_id"))
    private List<String> languages;
}
