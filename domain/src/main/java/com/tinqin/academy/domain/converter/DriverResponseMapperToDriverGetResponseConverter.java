package com.tinqin.academy.domain.converter;

import com.tinqin.academy.api.models.get.requests.DriverGetRequest;
import com.tinqin.academy.api.models.get.response.DriverGetResponse;
import com.tinqin.academy.db.service.mapper.DriverResponseMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DriverResponseMapperToDriverGetResponseConverter implements Converter<DriverResponseMapper, DriverGetResponse> {
    @Override
    public DriverGetResponse convert(DriverResponseMapper source) {
        return DriverGetResponse.builder().firstName(source.getFirstName())
                .lastName(source.getLastName())
                .constructorPoints(source.getDriverPoints())
                .numberOfChampionShips(source.getNumberOfChampionShips())
                .teamName(source.getTeamName())
                .build();
    }
}
