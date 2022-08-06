package error;

import com.tinqin.academy.api.base.Error;
import org.springframework.http.HttpStatus;

public class NoSuchTeamError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "No such team!";
    }
}
