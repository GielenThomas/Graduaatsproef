package com.dnd.charactergenerator.repository;

import com.dnd.charactergenerator.domain.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterClassRepository extends JpaRepository<CharacterClass, UUID> {


}
