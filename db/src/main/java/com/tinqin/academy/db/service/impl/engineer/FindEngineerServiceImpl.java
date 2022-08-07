package com.tinqin.academy.db.service.impl.engineer;

import com.tinqin.academy.api.models.get.requests.EngineerGetRequest;
import com.tinqin.academy.db.repositories.EngineerRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.EngineerResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindEngineerServiceImpl implements FindService<EngineerGetRequest, EngineerResponseMapper> {
    private final EngineerRepository engineerRepository;

    public FindEngineerServiceImpl(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    @Override
    public List<EngineerResponseMapper> find(EngineerGetRequest input) {
        return Stream.of(engineerRepository.getEngineerByFirstName(input.getEngineerName()))
                .filter(Objects::nonNull)
                .map(engineer -> EngineerResponseMapper.builder()
                        .firstName(engineer.getFirstName())
                        .lastName(engineer.getLastName())
                        .team(engineer.getTeam().getTeamName())
                        .position(engineer.getPosition().getPositionName())
                        .build())
                .collect(Collectors.toList());
    }
}
