package service.interfaces;

import com.tinqin.academy.api.operations.OperationInput;
import com.tinqin.academy.api.operations.OperationResult;

import java.util.List;

public interface FindService<I extends OperationInput,R extends OperationResult> {
    List<R> find(I input);
}
