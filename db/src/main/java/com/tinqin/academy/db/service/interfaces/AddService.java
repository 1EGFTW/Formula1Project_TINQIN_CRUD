package com.tinqin.academy.db.service.interfaces;

import com.tinqin.academy.api.base.OperationInput;

public interface AddService<I extends OperationInput> {
    Long add(I request);
}
