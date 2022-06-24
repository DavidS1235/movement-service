package com.nttdata.movementservice.model.entity;

import java.util.Date;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "MOVEMENT")
/**
 * Esta clase implementa un registro movimiento en la base de datos
 */
public class Movement {
<<<<<<< HEAD
    @Id
    private String id;
    private String idProductDestiny;
    private Date fecMovement;
    private String idTypeMovement;
    private Number numAmount;
    private String idProductOrigin;
    private Boolean fgActive;
=======
  @Id
  private String id;
  private String idProduct;
  private Date fecMovement;
  private String idTypeMovement;
  private Number numAmount;
  private Boolean fgActive;
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
}
