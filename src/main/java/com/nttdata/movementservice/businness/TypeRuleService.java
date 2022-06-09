package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.RequestTypeRule;
import com.nttdata.movementservice.model.api.ResponseTypeMovement;
import com.nttdata.movementservice.model.api.ResponseTypeRule;
import reactor.core.publisher.Mono;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa api y la logica de negocios
 */
public interface TypeRuleService {

    Mono<ResponseTypeRule> createTypeRule(RequestTypeRule request);
    Mono<ResponseTypeRule> findAllTypeRule(RequestTypeRule request);
    Mono<ResponseTypeRule> findTypeRule(RequestTypeRule request);
    Mono<ResponseTypeRule> deleteTypeRule(RequestTypeRule request);

}
