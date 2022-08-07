package com.tinqin.academy.db.service.impl.position;

import com.tinqin.academy.db.repositories.PositionRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
import org.springframework.stereotype.Service;

@Service("position")
public class DeletePositionServiceImpl implements DeleteService {
    private final PositionRepository positionRepository;

    public DeletePositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
    }
}
