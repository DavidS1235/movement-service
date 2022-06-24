package com.nttdata.movementservice.controller;

import com.nttdata.movementservice.businness.TypeMovementService;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.ResponseTypeMovement;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movement/type")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
/**
 * Esta clase es la encargada de exponer las funcionalidades
 */
public class TypeMovementController {
  @Autowired
  private TypeMovementService typeMovementService;

<<<<<<< HEAD
    @PostMapping(
            value = "/create",
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
            value = "/findall",
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
            value = "/find",
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
            value = "/delete",
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
=======
  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/create",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar la creacion de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseTypeMovement> createTypeMovement(@RequestBody RequestTypeMovement request) {
    return typeMovementService.createTypeMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/findall",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar la busqueda de registros
   * @param request objeto recibido de la api con los filtros de busqueda
   * @return Mono<ResponseTypeMovement> lista de registros obtenidos
   */
  public Mono<ResponseTypeMovement> findAllTypeMovement(@RequestBody RequestTypeMovement request) {
    return typeMovementService.findAllTypeMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/find",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar la busqueda de un registro
   * @param request objeto recibido de la api con los filtros de busqueda
   * @return Mono<ResponseTypeMovement> lista de registros obtenidos
   */
  public Mono<ResponseTypeMovement> findTypeMovement(@RequestBody RequestTypeMovement request) {
    return typeMovementService.findTypeMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/delete",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar el borrado logico de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
   */

  public Mono<ResponseTypeMovement> deleteTypeMovement(@RequestBody RequestTypeMovement request) {
    return typeMovementService.deleteTypeMovement(request);
  }
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d

}
