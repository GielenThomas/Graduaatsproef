package com.dnd.charactergenerator.service;

import com.dnd.charactergenerator.domain.Feat;
import com.dnd.charactergenerator.repository.FeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeatService {

    private final FeatRepository featRepository;
    public FeatService(FeatRepository featRepository) {
        this.featRepository = featRepository;
    }
    public List<Feat> getAllFeats() {
        return featRepository.findAll();
    }
    public Optional<Feat> getFeatById(UUID id) {
        return featRepository.findById(id);
    }

    public void createFeat(Feat feat) {
        featRepository.save(feat);
    }

    public void deleteFeat(UUID id) {
        featRepository.deleteById(id);
    }

    public Optional<Feat> updateFeat(UUID id, Feat feat) {
        feat.setId(id);
        featRepository.save(feat);
        return Optional.of(feat);
    }
}
