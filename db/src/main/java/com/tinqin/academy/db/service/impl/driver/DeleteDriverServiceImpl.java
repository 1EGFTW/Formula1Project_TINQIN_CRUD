package com.tinqin.academy.db.service.impl.driver;

import com.tinqin.academy.db.repositories.DriverRepository;
import com.tinqin.academy.db.service.interfaces.DeleteService;
import org.springframework.stereotype.Service;

@Service("driver")
public class DeleteDriverServiceImpl implements DeleteService {
    private final DriverRepository driverRepository;

    public DeleteDriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }
}
