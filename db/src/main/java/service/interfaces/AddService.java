package service.interfaces;

import com.tinqin.academy.api.operations.OperationInput;

public interface AddService<I extends OperationInput> {
    Long add(I request);
}
