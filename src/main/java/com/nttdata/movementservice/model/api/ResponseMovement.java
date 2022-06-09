package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.Movement;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa una respuesta de movimiento de la api
 */
public class ResponseMovement {

    ResponseAudit audit;
    List<Movement> list;
}
