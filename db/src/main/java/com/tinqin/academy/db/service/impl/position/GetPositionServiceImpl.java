package com.tinqin.academy.db.service.impl.position;

import com.tinqin.academy.db.entities.Position;
import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.PositionResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetPositionServiceImpl implements GetService<PositionResponseMapper> {
    private final PositionRepository positionRepository;

    public GetPositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public PositionResponseMapper getById(Long id) {
        Position position=positionRepository.findById(id).orElseThrow();
        return PositionResponseMapper.builder()
                .positionName(position.getPositionName())
                .build();
    }
}
