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
            spell.setId(id);
            spellRepository.save(spell);
            return Optional.of(spell);
        }



}
