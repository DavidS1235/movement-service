package com.nttdata.movementservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
@Document(collection = "TYPE_MOVEMENT")
=======
@Document(collection = "typeMovement")
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
/**
 * Esta clase implementa un registro tipo de movimiento en la base de datos
 */
public class TypeMovement {

  @Id
  private String id;
  private String code;
  private String name;
  private Boolean fgActive;

}
