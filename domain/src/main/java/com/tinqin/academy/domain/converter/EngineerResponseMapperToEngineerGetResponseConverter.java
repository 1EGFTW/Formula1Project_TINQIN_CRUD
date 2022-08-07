package com.tinqin.academy.domain.converter;

import com.tinqin.academy.api.models.get.response.EngineerGetResponse;
import com.tinqin.academy.db.service.mapper.EngineerResponseMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EngineerResponseMapperToEngineerGetResponseConverter implements Converter<EngineerResponseMapper, EngineerGetResponse> {
    @Override
    public EngineerGetResponse convert(EngineerResponseMapper source) {
        return EngineerGetResponse.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .team(source.getTeam())
                .position(source.getPosition())
                .build();
    }
}
