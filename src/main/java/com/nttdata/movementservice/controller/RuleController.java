package com.nttdata.movementservice.controller;

import com.nttdata.movementservice.businness.MovementService;
import com.nttdata.movementservice.businness.RuleService;
import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestRule;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.api.ResponseRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rule")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
/**
 * Esta clase es la encargada de exponer las funcionalidades
 */
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la creacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
    public Mono<ResponseRule> createRule(@RequestBody RequestRule request){
        return ruleService.createRule(request);
    }

    @PostMapping(
            value = "/findall",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la busqueda de registros
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseMovement> lista de registros obtenidos
     */
    public Mono<ResponseRule> findAllRule(@RequestBody RequestRule request){
        return ruleService.findAllRule(request);
    }

    @PostMapping(
            value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar el borrado logico de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
    public Mono<ResponseRule> deleteRule(@RequestBody RequestRule request){
        return ruleService.deleteRule(request);
    }
}
