package com.tinqin.academy.db.service.impl.driver;

import com.tinqin.academy.db.entities.Driver;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.service.interfaces.GetService;
import com.tinqin.academy.db.service.mapper.DriverResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetDriverServiceImpl implements GetService<DriverResponseMapper> {
    private final DriverRepository driverRepository;

    public GetDriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverResponseMapper getById(Long id) {
        Driver driver=driverRepository.findById(id).orElseThrow();
        return DriverResponseMapper.builder()
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .driverPoints(String.valueOf(driver.getDriverPoints()))
                .numberOfChampionShips(String.valueOf(driver.getChampionships()))
                .teamName(driver.getTeam().getTeamName())
                .build();
    }
}
