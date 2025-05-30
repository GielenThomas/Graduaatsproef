package com.dnd.charactergenerator.repository;

import com.dnd.charactergenerator.domain.Feat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatRepository extends JpaRepository<Feat, UUID> {
}
