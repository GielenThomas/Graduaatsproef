package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.domain.Spell;
import com.dnd.charactergenerator.repository.SpellRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpellService {

    private final SpellRepository spellRepository;

    public SpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }


        public List<Spell> getAllSpells(){
            return spellRepository.findAll();
        }

        public Optional<Spell> getSpell(UUID id){
            return spellRepository.findById(id);
        }

        public void CreateSpell(Spell spell){
            spellRepository.save(spell);
        }

        public void deleteSpell(UUID id){spellRepository.deleteById(id);}

        public Optional<Spell> updateSpell(UUID id, Spell spell){
        return spellRepository.findById(id).map(existingSpell -> {
            Spell updatedSpell = existingSpell.toBuilder()
                    .name(spell.getName() != null ? spell.getName() : existingSpell.getName())
                    .level(spell.getLevel() != null ? spell.getLevel() : existingSpell.getLevel())
                    .school(spell.getSchool() != null ? spell.getSchool() : existingSpell.getSchool())
                    .castingTime(spell.getCastingTime() != null ? spell.getCastingTime() : existingSpell.getCastingTime())
                    .spellRange(spell.getSpellRange() != null ? spell.getSpellRange() : existingSpell.getSpellRange())
                    .duration(spell.getDuration() != null ? spell.getDuration() : existingSpell.getDuration())
                    .description(spell.getDescription() != null ? spell.getDescription() : existingSpell.getDescription())
                    .components(spell.getComponents() != null ? spell.getComponents() : existingSpell.getComponents())
                    .higherLevels(spell.getHigherLevels() != null ? spell.getHigherLevels() : existingSpell.getHigherLevels())
                    .build();
            return spellRepository.save(updatedSpell);
        });
        }



}
