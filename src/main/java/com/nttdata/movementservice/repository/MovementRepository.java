package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.entity.Movement;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovementRepository {
    Movement createMovement(RequestMovement request);
    List<Movement> findAllMovement(RequestMovement request);
    Movement updateMovement(RequestMovement request);
    Movement deleteMovement(RequestMovement request);
}