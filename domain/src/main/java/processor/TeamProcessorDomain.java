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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import service.interfaces.*;

import java.util.NoSuchElementException;

@Service
public class TeamProcessorDomain implements TeamProcessor {
    private final ConversionService conversionService;

    private final FindService<TeamGetRequest,TeamGetResponse> findService;
    private final AddService<TeamCreateRequest> addService;
    private final DeleteService deleteService;
    private final UpdateService<TeamUpdateRequest> updateService;
    private final GetService<TeamGetResponse> getService;

    public TeamProcessorDomain(ConversionService conversionService, FindService<TeamGetRequest, TeamGetResponse> findService, AddService<TeamCreateRequest> addService, DeleteService deleteService, UpdateService<TeamUpdateRequest> updateService, GetService<TeamGetResponse> getService) {
        this.conversionService = conversionService;
        this.findService = findService;
        this.addService = addService;
        this.deleteService = deleteService;
        this.updateService = updateService;
        this.getService = getService;
    }


    @Override
    public Either<Error, TeamGetResponse> process(TeamGetRequest teamName) {
        return Try.of(()->{
            return conversionService.convert(findService.find(teamName),TeamGetResponse.class);
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
                    return conversionService.convert(getService.getById(id),TeamGetResponse.class);
                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchTeamError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, Long> processAdd(TeamCreateRequest teamCreateRequest) {
        return Try.of(()->addService.add(teamCreateRequest))
                .toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof NoSuchElementException)
                        return new NoSuchTeamError();
                    return new GeneralServerError();
                });
    }

    @Override
    public HttpStatus processDelete(Long id) {
        deleteService.delete(id);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus processUpdate(Long id,TeamUpdateRequest teamUpdateRequest) {
        updateService.update(id,teamUpdateRequest);
        return HttpStatus.OK;
    }
}
