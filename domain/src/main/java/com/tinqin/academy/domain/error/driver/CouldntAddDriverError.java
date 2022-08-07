package com.tinqin.academy.domain.error.driver;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class CouldntAddDriverError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Couldn't add driver";
    }
}
