package com.tinqin.academy.domain.error.engineer;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class NoSuchEngineerError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "No such engineer";
    }
}
