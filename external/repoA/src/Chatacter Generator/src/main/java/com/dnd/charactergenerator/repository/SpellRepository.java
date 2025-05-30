package com.dnd.charactergenerator.repository;

import com.dnd.charactergenerator.domain.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpellRepository extends JpaRepository<Spell, UUID> {

}
