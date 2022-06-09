package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.entity.TypeMovement;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa una respuesta de tipo de movimiento de la api
 */
public class ResponseTypeMovement {

    ResponseAudit audit;
    List<TypeMovement> list;
}
