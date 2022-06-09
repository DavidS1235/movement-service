package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.*;
import com.nttdata.movementservice.model.entity.Rule;
import com.nttdata.movementservice.model.entity.TypeRule;
import com.nttdata.movementservice.repository.RuleRepository;
import com.nttdata.movementservice.repository.TypeRuleRepository;
import com.nttdata.movementservice.utils.Constant;
import com.nttdata.movementservice.utils.MovementValidation;
import com.nttdata.movementservice.utils.TypeValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    /**
     * Este método se encarga de solicitar la creacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeRule> objeto devuelto por la base de datos
     */
    public Mono<ResponseRule> createRule(RequestRule request) {
        ResponseRule response = new ResponseRule();
        Rule rule;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            rule = ruleRepository.createRule(request);

            if(null != rule){
                if(!rule.getId().isEmpty()){
                    response.getAudit().setCode(Constant.CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(rule);
                }
                else{
                    response.getAudit().setCode(Constant.CODE_ERROR);
                }
            } else {
                response.getAudit().setCode(Constant.CODE_UNKNOWN);
            }
        } catch(Exception e){
            response.getAudit().setCode(Constant.CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }

    @Override
    /**
     * Este método se encarga de solicitar la busqueda de registros
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseTypeRule> lista de registros obtenidos
     */
    public Mono<ResponseRule> findAllRule(RequestRule request) {
        ResponseRule response = new ResponseRule();
        List<Rule> list;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            list = ruleRepository.findAllRule(request);

            if(null != list){
                if(0 < list.size()){
                    response.getAudit().setCode(Constant.CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().addAll(list);
                }
                else{
                    response.getAudit().setCode(Constant.CODE_NO_DATA);
                }
            } else{
                response.getAudit().setCode(Constant.CODE_UNKNOWN);
            }

        } catch(Exception e){
            response.getAudit().setCode(Constant.CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }

    @Override
    /**
     * Este método se encarga de solicitar el borrado logico de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeRule> objeto devuelto por la base de datos
     */
    public Mono<ResponseRule> deleteRule(RequestRule request) {
        ResponseRule response = new ResponseRule();
        Rule rule;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            rule = ruleRepository.deleteRule(request);

            if(null != rule){
                if(request.getId() != rule.getId()){
                    response.getAudit().setCode(Constant.CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(rule);
                }
                else{
                    response.getAudit().setCode(Constant.CODE_ERROR);
                }
            } else {
                response.getAudit().setCode(Constant.CODE_UNKNOWN);
            }
        } catch(Exception e){
            response.getAudit().setCode(Constant.CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }


}
