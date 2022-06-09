package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestTypeRule;
import com.nttdata.movementservice.model.api.ResponseAudit;
import com.nttdata.movementservice.model.api.ResponseTypeRule;
import com.nttdata.movementservice.model.entity.TypeRule;
import com.nttdata.movementservice.repository.TypeRuleRepository;
import com.nttdata.movementservice.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TypeRuleServiceImpl implements TypeRuleService {

    @Autowired
    private TypeRuleRepository typeRuleRepository;

    @Override
    /**
     * Este método se encarga de solicitar la creacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeRule> objeto devuelto por la base de datos
     */
    public Mono<ResponseTypeRule> createTypeRule(RequestTypeRule request) {
        ResponseTypeRule response = new ResponseTypeRule();
        TypeRule typeRule;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            typeRule = typeRuleRepository.createTypeRule(request);

            if(null != typeRule){
                if(!typeRule.getId().isEmpty()){
                    response.getAudit().setCode(Constant.CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(typeRule);
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
    public Mono<ResponseTypeRule> findAllTypeRule(RequestTypeRule request) {
        ResponseTypeRule response = new ResponseTypeRule();
        List<TypeRule> list;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            list = typeRuleRepository.findAllTypeRule(request);

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
     * Este método se encarga de solicitar la busqueda de un registro
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseTypeRule> lista de registros obtenidos
     */
    public Mono<ResponseTypeRule> findTypeRule(RequestTypeRule request) {
        ResponseTypeRule response = new ResponseTypeRule();
        List<TypeRule> list;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            list = typeRuleRepository.findTypeRule(request);

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
    public Mono<ResponseTypeRule> deleteTypeRule(RequestTypeRule request) {
        ResponseTypeRule response = new ResponseTypeRule();
        TypeRule typeRule;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            typeRule = typeRuleRepository.deleteTypeRule(request);

            if(null != typeRule){
                if(request.getId() != typeRule.getId()){
                    response.getAudit().setCode(Constant.CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(typeRule);
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
