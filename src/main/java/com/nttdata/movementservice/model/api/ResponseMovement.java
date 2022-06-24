package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.Movement;
<<<<<<< HEAD
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
=======
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
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

<<<<<<< HEAD
    ResponseAudit audit;
    List<Movement> list;

    public void ResponseMovement(){
        setAudit(new ResponseAudit());
        list = new ArrayList<>();
    }
=======
  ResponseAudit audit;
  List<Movement> list;
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
}
