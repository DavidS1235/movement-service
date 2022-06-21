package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.Movement;
import java.util.List;
import lombok.*;

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
