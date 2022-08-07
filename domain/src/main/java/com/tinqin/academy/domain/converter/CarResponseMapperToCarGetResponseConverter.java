package com.tinqin.academy.domain.converter;

import com.tinqin.academy.api.models.get.response.CarGetResponse;
import com.tinqin.academy.db.service.mapper.CarResponseMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarResponseMapperToCarGetResponseConverter implements Converter<CarResponseMapper, CarGetResponse> {
    @Override
    public CarGetResponse convert(CarResponseMapper source) {
        return CarGetResponse.builder()
                .modelName(source.getModelName())
                .horsepower(source.getHorsepower())
                .torque(source.getTorque())
                .teamName(source.getTeamName())
                .build();
    }
}
