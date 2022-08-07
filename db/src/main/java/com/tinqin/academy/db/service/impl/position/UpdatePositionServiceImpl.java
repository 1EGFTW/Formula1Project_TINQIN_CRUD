package com.tinqin.academy.db.service.impl.position;

import com.tinqin.academy.api.models.update.PositionUpdateRequest;
import com.tinqin.academy.db.entities.Position;
import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.service.interfaces.UpdateService;
import org.springframework.stereotype.Service;

@Service
public class UpdatePositionServiceImpl implements UpdateService<PositionUpdateRequest> {
    private final PositionRepository positionRepository;

    public UpdatePositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public void update(Long id, PositionUpdateRequest editRequest) {
        Position position=positionRepository.findById(id).orElseThrow();
        position.setPositionName(editRequest.getPositionName());
        positionRepository.save(position);
    }
}
