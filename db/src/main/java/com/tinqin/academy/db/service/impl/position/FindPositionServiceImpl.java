package com.tinqin.academy.db.service.impl.position;

import com.tinqin.academy.api.models.get.requests.PositionGetRequest;
import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.PositionResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class FindPositionServiceImpl implements FindService<PositionGetRequest, PositionResponseMapper> {
    private final PositionRepository positionRepository;

    public FindPositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<PositionResponseMapper> find(PositionGetRequest input) {
        return Stream.of(positionRepository.getPositionByPositionName(input.getPositionName()))
                .filter(Objects::nonNull)
                .map(position -> PositionResponseMapper.builder()
                        .positionName(position.getPositionName())
                        .build())
                .collect(Collectors.toList());
    }
}
