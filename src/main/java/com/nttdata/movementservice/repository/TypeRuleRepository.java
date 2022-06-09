package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.RequestTypeRule;
import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.model.entity.TypeRule;

import java.util.List;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa de negocios y datos
 */
public interface TypeRuleRepository {
    TypeRule createTypeRule(RequestTypeRule request);
    List<TypeRule> findAllTypeRule(RequestTypeRule request);
    List<TypeRule> findTypeRule(RequestTypeRule request);
    TypeRule findTypeRuleByCode(String code);
    TypeRule deleteTypeRule(RequestTypeRule request);
}
