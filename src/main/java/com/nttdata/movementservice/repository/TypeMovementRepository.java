package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.entity.TypeMovement;

import java.util.List;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa de negocios y datos
 */
public interface TypeMovementRepository {
    TypeMovement createTypeMovement(RequestTypeMovement request);
    List<TypeMovement> findAllTypeMovement(RequestTypeMovement request);
    List<TypeMovement> findTypeMovement(RequestTypeMovement request);

    TypeMovement deleteTypeMovement(RequestTypeMovement request);
}
