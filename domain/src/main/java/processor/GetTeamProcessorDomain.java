package processor;

import com.tinqin.academy.api.base.Error;
import com.tinqin.academy.api.models.create.TeamCreateRequest;
import com.tinqin.academy.api.models.get.requests.TeamGetRequest;
import com.tinqin.academy.api.models.get.response.TeamGetResponse;
import com.tinqin.academy.api.models.update.TeamUpdateRequest;
import com.tinqin.academy.api.operation.TeamProcessor;
import error.GeneralServerError;
import error.NoSuchTeamError;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import service.impl.team.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class GetTeamProcessorDomain implements TeamProcessor {
    private final FindTeamServiceImpl findTeamService;
    private final AddTeamServiceImpl addTeamService;
    private final DeleteTeamServiceImpl deleteTeamService;
    private final UpdateTeamServiceImpl updateTeamServiceImpl;
    private final GetTeamServiceImpl getTeamService;
    private final ConversionService conversionService;

    public GetTeamProcessorDomain(FindTeamServiceImpl findTeamService, AddTeamServiceImpl addTeamService, DeleteTeamServiceImpl deleteTeamService, UpdateTeamServiceImpl updateTeamServiceImpl, GetTeamServiceImpl getTeamService, ConversionService conversionService) {
        this.findTeamService = findTeamService;
        this.addTeamService = addTeamService;
        this.deleteTeamService = deleteTeamService;
        this.updateTeamServiceImpl = updateTeamServiceImpl;
        this.getTeamService = getTeamService;
        this.conversionService = conversionService;
    }


    @Override
    public Either<Error, TeamGetResponse> process(TeamGetRequest teamGetRequest) {
        return Try.of(()->{
            return conversionService.convert(findTeamService.find(teamGetRequest),TeamGetResponse.class);
        }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchTeamError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, TeamGetResponse> processById(Long id) {
        return Try.of(()->{
                    return conversionService.convert(getTeamService.getById(id),TeamGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchTeamError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(TeamCreateRequest teamCreateRequest) {
        return Try.of(()->addTeamService.add(teamCreateRequest))
                .toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchTeamError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteTeamService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id,TeamUpdateRequest teamUpdateRequest) {
        updateTeamServiceImpl.update(id,teamUpdateRequest);
        return HttpStatus.OK;
    }
}
