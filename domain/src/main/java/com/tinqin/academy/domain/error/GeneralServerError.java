package com.tinqin.academy.domain.error;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class GeneralServerError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "UNHANDLED EXCEPTION!!!";
    }
}
