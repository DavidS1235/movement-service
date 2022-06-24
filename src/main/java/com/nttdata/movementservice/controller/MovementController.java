package com.nttdata.movementservice.controller;

import com.nttdata.movementservice.businness.MovementService;
import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movement")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
/**
 * Esta clase es la encargada de exponer las funcionalidades
 */
public class MovementController {
  @Autowired
  private MovementService movementService;

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/create",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar la creacion de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseMovement> createMovement(@RequestBody RequestMovement request) {
    return movementService.createMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/findall",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar la busqueda de registros
   * @param request objeto recibido de la api con los filtros de busqueda
   * @return Mono<ResponseMovement> lista de registros obtenidos
   */
  public Mono<ResponseMovement> findAllMovement(@RequestBody RequestMovement request) {
    return movementService.findAllMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/update",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar la actualizacion de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseMovement> updateMovement(@RequestBody RequestMovement request) {
    return movementService.updateMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/delete",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de solicitar el borrado logico de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseMovement> deleteMovement(@RequestBody RequestMovement request) {
    return movementService.deleteMovement(request);
  }

  @CircuitBreaker(name = "movement")
  @PostMapping(
          value = "/callClient",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  /**
   * Este método se encarga de consumir un servicio externo
   * Este ejemplo consume al metodo api findall del mismo
   * @param request objeto recibido de la api
   * @return Mono<ResponseMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseMovement> callClient(@RequestBody RequestMovement request) {
    return movementService.callClient(request);
  }
}
