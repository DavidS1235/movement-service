package com.nttdata.movementservice.model.api;

import java.util.Date;
import lombok.*;

//@Data
@ToString
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa un requerimiento de movimiento de la api
 */
public class RequestMovement {
<<<<<<< HEAD
    private String id;
    private String idProductDestiny;
    private Date fecMovement;
    private String idTypeMovement;
    private Number numAmount;
    private String idProductOrigin;
    private Boolean fgActive;
=======
  private String id;
  private String idProduct;
  private Date fecMovement;
  private String idTypeMovement;
  private Number numAmount;
  private Boolean fgActive;
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d

}
