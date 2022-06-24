package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.ResponseTypeMovement;
import reactor.core.publisher.Mono;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa api y la logica de negocios
 */
public interface TypeMovementService {

<<<<<<< HEAD
    Mono<ResponseTypeMovement> createTypeMovement(RequestTypeMovement request);
    Mono<ResponseTypeMovement> findAllTypeMovement(RequestTypeMovement request);
    Mono<ResponseTypeMovement> findTypeMovement(RequestTypeMovement request);
    Mono<ResponseTypeMovement> deleteTypeMovement(RequestTypeMovement request);
=======
  Mono<ResponseTypeMovement> createTypeMovement(RequestTypeMovement request);

  Mono<ResponseTypeMovement> findAllTypeMovement(RequestTypeMovement request);

  Mono<ResponseTypeMovement> findTypeMovement(RequestTypeMovement request);

  Mono<ResponseTypeMovement> deleteTypeMovement(RequestTypeMovement request);
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d

}
