package com.tinqin.academy.db.service.impl.driver;

import com.tinqin.academy.api.models.get.requests.DriverGetRequest;
import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.service.interfaces.FindService;
import com.tinqin.academy.db.service.mapper.DriverResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindDriverServiceImpl implements FindService<DriverGetRequest, DriverResponseMapper> {
    private final DriverRepository driverRepository;

    public FindDriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<DriverResponseMapper> find(DriverGetRequest input) {
        return Stream.of(driverRepository.getDriverByFirstNameAndLastName(input.getFirstName(),input.getLastName()))
                .filter(Objects::nonNull)
                .map(driver -> DriverResponseMapper.builder()
                        .firstName(driver.getFirstName())
                        .lastName(driver.getLastName())
                        .driverPoints(String.valueOf(driver.getDriverPoints()))
                        .numberOfChampionShips(String.valueOf(driver.getChampionships()))
                        .teamName(driver.getTeam().getTeamName())
                        .build())
                .collect(Collectors.toList());
    }
}
