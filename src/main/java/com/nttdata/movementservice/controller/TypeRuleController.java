package com.nttdata.movementservice.controller;

import com.nttdata.movementservice.businness.TypeMovementService;
import com.nttdata.movementservice.businness.TypeRuleService;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.RequestTypeRule;
import com.nttdata.movementservice.model.api.ResponseTypeMovement;
import com.nttdata.movementservice.model.api.ResponseTypeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rule/type")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
/**
 * Esta clase es la encargada de exponer las funcionalidades
 */
public class TypeRuleController {
    @Autowired
    private TypeRuleService typeRuleService;

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la creacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeRule> objeto devuelto por la base de datos
     */
    public Mono<ResponseTypeRule> createTypeRule(@RequestBody RequestTypeRule request){
        return typeRuleService.createTypeRule(request);
    }

    @PostMapping(
            value = "/findall",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la busqueda de registros
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseTypeMovement> lista de registros obtenidos
     */
    public Mono<ResponseTypeRule> findAllTypeRule(@RequestBody RequestTypeRule request){
        return typeRuleService.findAllTypeRule(request);
    }

    @PostMapping(
            value = "/find",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la busqueda de un registro
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseTypeMovement> lista de registros obtenidos
     */
    public Mono<ResponseTypeRule> findTypeRule(@RequestBody RequestTypeRule request){
        return typeRuleService.findTypeRule(request);
    }

    @PostMapping(
            value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar el borrado logico de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
     */
    public Mono<ResponseTypeRule> deleteTypeRule(@RequestBody RequestTypeRule request){
        return typeRuleService.deleteTypeRule(request);
    }

}
