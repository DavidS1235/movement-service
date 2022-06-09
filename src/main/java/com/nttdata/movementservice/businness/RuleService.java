package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestRule;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.api.ResponseRule;
import com.nttdata.movementservice.utils.MovementValidation;
import com.nttdata.movementservice.utils.TypeValidationResult;
import reactor.core.publisher.Mono;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa api y la logica de negocios
 */
public interface RuleService {

    Mono<ResponseRule> createRule(RequestRule request);
    Mono<ResponseRule> findAllRule(RequestRule request);
    Mono<ResponseRule> deleteRule(RequestRule request);


}
