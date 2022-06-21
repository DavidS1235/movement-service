package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.TypeMovement;
import java.util.List;
import lombok.*;

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
