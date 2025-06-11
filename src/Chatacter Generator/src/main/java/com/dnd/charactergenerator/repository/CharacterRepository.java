package com.dnd.charactergenerator.repository;

import com.dnd.charactergenerator.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, UUID> {
}
