package com.tinqin.academy.domain.converter;

import com.tinqin.academy.api.models.get.response.SeasonGetResponse;
import com.tinqin.academy.db.service.mapper.SeasonResponseMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeasonResponseMapperToSeasonGetResponseConverter implements Converter<SeasonResponseMapper, SeasonGetResponse> {
    @Override
    public SeasonGetResponse convert(SeasonResponseMapper source) {
        return SeasonGetResponse.builder()
                .year(source.getYear())
                .races(source.getRaces())
                .winner(source.getWinner())
                .build();
    }
}
