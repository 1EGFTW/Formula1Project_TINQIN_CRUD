package com.tinqin.academy.domain.error.race;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class NoSuchRaceError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "No such race";
    }
}
