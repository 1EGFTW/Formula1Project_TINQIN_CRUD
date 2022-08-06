package service.impl.car;

import com.tinqin.academy.api.models.create.CarCreateRequest;
import org.springframework.stereotype.Service;
import service.interfaces.AddService;

@Service
public class AddCarServiceImpl implements AddService<CarCreateRequest> {
    @Override
    public Long add(CarCreateRequest request) {
        return null;
    }
}
