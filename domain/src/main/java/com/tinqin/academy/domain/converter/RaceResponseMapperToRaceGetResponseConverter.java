package com.tinqin.academy.domain.converter;

import com.tinqin.academy.api.models.get.response.RaceGetResponse;
import com.tinqin.academy.db.service.mapper.RaceResponseMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RaceResponseMapperToRaceGetResponseConverter implements Converter<RaceResponseMapper, RaceGetResponse> {
    @Override
    public RaceGetResponse convert(RaceResponseMapper source) {
        return RaceGetResponse.builder()
                .winnerName(source.getWinnerFirstName()+" "+source.getWinnerLastName())
                .isCompleted(source.getIsCompleted())
                .date(source.getDate())
                .circuitName(source.getCircuitName())
                .seasons(source.getSeasons())
                .build();
    }
}
