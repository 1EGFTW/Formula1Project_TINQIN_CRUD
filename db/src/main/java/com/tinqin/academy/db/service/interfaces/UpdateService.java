package com.tinqin.academy.db.service.interfaces;

import com.tinqin.academy.api.base.OperationInput;

public interface UpdateService<I extends OperationInput> {
    void update(Long id,I editRequest);
}
