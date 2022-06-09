package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestRule;
import com.nttdata.movementservice.model.api.RequestTypeRule;
import com.nttdata.movementservice.model.entity.Rule;
import com.nttdata.movementservice.model.entity.TypeRule;

import java.util.List;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa de negocios y datos
 */
public interface RuleRepository {
    Rule createRule(RequestRule request);
    List<Rule> findAllRule(RequestRule request);
    List<Rule> findRule(RequestRule request);
    Rule findRuleByCode(String code);
    Rule deleteRule(RequestRule request);
}
