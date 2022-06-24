package com.nttdata.movementservice.model.api;

import java.util.Date;
import lombok.*;


//@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa un registro de auditoria de la api
 */
public class ResponseAudit {

<<<<<<< HEAD
    String code;
    String message;
    Date date;

    public void ResponseAudit(){
        date = new Date();
    }
=======
  String code;
  String message;
  Date date;
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
}
