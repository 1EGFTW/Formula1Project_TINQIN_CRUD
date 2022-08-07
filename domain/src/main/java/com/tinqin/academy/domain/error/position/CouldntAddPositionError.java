package com.tinqin.academy.domain.error.position;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class CouldntAddPositionError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Couldn't add position";
    }
}
