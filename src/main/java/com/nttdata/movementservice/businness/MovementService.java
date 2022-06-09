package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import reactor.core.publisher.Mono;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa api y la logica de negocios
 */
public interface MovementService {

    Mono<ResponseMovement> createMovement(RequestMovement request);
    Mono<ResponseMovement> findAllMovement(RequestMovement request);
    Mono<ResponseMovement> updateMovement(RequestMovement request);
    Mono<ResponseMovement> deleteMovement(RequestMovement request);

    Mono<ResponseMovement> callClient(RequestMovement request);
}
