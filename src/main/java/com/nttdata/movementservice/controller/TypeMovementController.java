package com.nttdata.movementservice.controller;

import com.nttdata.movementservice.businness.MovementService;
import com.nttdata.movementservice.businness.TypeMovementService;
import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.api.ResponseTypeMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
/**
 * Esta clase es la encargada de exponer las funcionalidades
 */
public class TypeMovementController {
    @Autowired
    private TypeMovementService typeMovementService;

    @PostMapping(
            value = "/createTypeMovement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la creacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
     */
    public Mono<ResponseTypeMovement> createTypeMovement(@RequestBody RequestTypeMovement request){
        return typeMovementService.createTypeMovement(request);
    }

    @PostMapping(
            value = "/findallTypeMovement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la busqueda de registros
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseTypeMovement> lista de registros obtenidos
     */
    public Mono<ResponseTypeMovement> findAllTypeMovement(@RequestBody RequestTypeMovement request){
        return typeMovementService.findAllTypeMovement(request);
    }

    @PostMapping(
            value = "/findTypeMovement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar la busqueda de un registro
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseTypeMovement> lista de registros obtenidos
     */
    public Mono<ResponseTypeMovement> findTypeMovement(@RequestBody RequestTypeMovement request){
        return typeMovementService.findTypeMovement(request);
    }

    @PostMapping(
            value = "/deleteTypeMovement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Este método se encarga de solicitar el borrado logico de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
     */
    public Mono<ResponseTypeMovement> deleteTypeMovement(@RequestBody RequestTypeMovement request){
        return typeMovementService.deleteTypeMovement(request);
    }

}
