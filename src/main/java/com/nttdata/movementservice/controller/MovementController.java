package com.nttdata.movementservice.controller;

import com.nttdata.movementservice.client.MovementClient;
import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.businness.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class MovementController {
    @Autowired
    private MovementService movementService;

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseMovement> createMovement(@RequestBody RequestMovement request){
        return movementService.createMovement(request);
    }

    @PostMapping(
            value = "/findall",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseMovement> findAllMovement(@RequestBody RequestMovement request){
        return movementService.findAllMovement(request);
    }

    @PostMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseMovement> updateMovement(@RequestBody RequestMovement request){
        return movementService.updateMovement(request);
    }

    @PostMapping(
            value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseMovement> deleteMovement(@RequestBody RequestMovement request){
        return movementService.deleteMovement(request);
    }

    @PostMapping(
            value = "/callClient",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseMovement> callClient(@RequestBody RequestMovement request){
        return movementService.callClient(request);
    }
}
