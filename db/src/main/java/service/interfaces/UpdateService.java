package service.interfaces;

import com.tinqin.academy.api.operations.OperationInput;

public interface UpdateService<I extends OperationInput> {
    void update(Long id,I editRequest);
}
