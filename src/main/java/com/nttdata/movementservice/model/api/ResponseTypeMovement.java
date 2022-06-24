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

<<<<<<< HEAD
    ResponseAudit audit;
    List<TypeMovement> list;

    public void ResponseTypeMovement(){
        setAudit(new ResponseAudit());
    }
=======
  ResponseAudit audit;
  List<TypeMovement> list;
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
}
