package com.dnd.charactergenerator.mapper;

import com.dnd.charactergenerator.domain.Feat;
import com.dnd.model.FeatRequest;
import com.dnd.model.FeatResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatMapper {
    Feat toFeat(FeatRequest featRequest);
    FeatResponse toFeatResponse(Feat feat);
}
