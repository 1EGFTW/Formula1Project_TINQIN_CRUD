package com.tinqin.academy.db.service.impl.position;

import com.tinqin.academy.api.models.create.PositionCreateRequest;
import com.tinqin.academy.db.entities.Position;
import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.service.interfaces.AddService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AddPositionServiceImpl implements AddService<PositionCreateRequest> {
    private final PositionRepository positionRepository;

    public AddPositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Long add(PositionCreateRequest request) {
        return Stream.of(positionRepository.getPositionByPositionName(request.getPositionName()))
                .peek(position -> {
                    if(position==null){
                        positionRepository.save(Position.builder()
                                        .positionName(request.getPositionName())
                                .build());
                    }
                })
                .map(position -> positionRepository.getPositionByPositionName(request.getPositionName()))
                .findFirst()
                .orElseThrow()
                .getId_position();
    }
}
