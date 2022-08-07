package com.tinqin.academy.domain.error.car;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class NoSuchCarError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "No such car!";
    }
}
