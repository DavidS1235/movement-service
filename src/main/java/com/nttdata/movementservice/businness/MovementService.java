package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import reactor.core.publisher.Mono;

public interface MovementService {

    Mono<ResponseMovement> createMovement(RequestMovement request);
    Mono<ResponseMovement> findAllMovement(RequestMovement request);
    Mono<ResponseMovement> updateMovement(RequestMovement request);
    Mono<ResponseMovement> deleteMovement(RequestMovement request);

    Mono<ResponseMovement> callClient(RequestMovement request);
}
