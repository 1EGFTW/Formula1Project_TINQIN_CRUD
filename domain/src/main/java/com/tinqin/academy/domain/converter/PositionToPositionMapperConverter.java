package com.tinqin.academy.domain.converter;

import com.tinqin.academy.api.models.get.response.PositionGetResponse;
import com.tinqin.academy.db.service.mapper.PositionResponseMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionToPositionMapperConverter implements Converter<PositionResponseMapper, PositionGetResponse> {
    @Override
    public PositionGetResponse convert(PositionResponseMapper source) {
        return PositionGetResponse.builder()
                .positionName(source.getPositionName())
                .build();
    }
}
