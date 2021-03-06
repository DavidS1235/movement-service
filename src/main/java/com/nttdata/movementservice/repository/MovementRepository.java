package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.entity.Movement;
import java.util.List;

/**
 * Esta clase es la interfaz que permite respetar la separacion entre
 * la capa de negocios y datos
 */
public interface MovementRepository {
  Movement createMovement(RequestMovement request);

  List<Movement> findAllMovement(RequestMovement request);

  Movement updateMovement(RequestMovement request);

  Movement deleteMovement(RequestMovement request);

  List<Movement> getListMovementDay(String idProduct, Number dayMonth, String codesTypeMovement);

  List<Movement> getListMovementMonth(String idProduct, String codesTypeMovement);

}
