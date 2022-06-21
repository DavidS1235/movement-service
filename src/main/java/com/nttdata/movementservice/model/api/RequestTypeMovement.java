package com.nttdata.movementservice.model.api;

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
public class RequestTypeMovement {
  private String id;
  private String code;
  private String name;
  private Boolean fgActive;

}
