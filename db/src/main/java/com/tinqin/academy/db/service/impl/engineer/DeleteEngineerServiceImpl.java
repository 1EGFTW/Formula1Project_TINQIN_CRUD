package com.tinqin.academy.db.service.impl.engineer;

import com.tinqin.academy.db.repositories.EngineerRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
import org.springframework.stereotype.Service;

@Service("engineer")
public class DeleteEngineerServiceImpl implements DeleteService {
    private final EngineerRepository engineerRepository;

    public DeleteEngineerServiceImpl(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    @Override
    public void delete(Long id) {
        engineerRepository.deleteById(id);
    }
}
