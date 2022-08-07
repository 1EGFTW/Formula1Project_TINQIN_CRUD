package com.tinqin.academy.db.service.impl.engineer;

import com.tinqin.academy.db.entities.Engineer;
import com.tinqin.academy.db.repositories.EngineerRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.EngineerResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetEngineerServiceImpl implements GetService<EngineerResponseMapper> {
    private final EngineerRepository engineerRepository;

    public GetEngineerServiceImpl(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    @Override
    public EngineerResponseMapper getById(Long id) {
        Engineer engineer=engineerRepository.findById(id).orElseThrow();
        return EngineerResponseMapper.builder()
                .firstName(engineer.getFirstName())
                .lastName(engineer.getLastName())
                .team(engineer.getTeam().getTeamName())
                .position(engineer.getPosition().getPositionName())
                .build();
    }
}
