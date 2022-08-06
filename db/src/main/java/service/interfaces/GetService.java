package service.interfaces;

import com.tinqin.academy.api.operations.OperationResult;

public interface GetService<R extends OperationResult> {
    R getById(Long id);
}
