package service.interfaces;

import com.tinqin.academy.api.base.OperationInput;
import com.tinqin.academy.api.base.OperationResult;

import java.util.List;

public interface FindService<I extends OperationInput,R extends OperationResult> {
    List<R> find(I input);
}
