package service.interfaces;

import com.tinqin.academy.api.base.OperationResult;

public interface GetService<R extends OperationResult> {
    R getById(Long id);
}
