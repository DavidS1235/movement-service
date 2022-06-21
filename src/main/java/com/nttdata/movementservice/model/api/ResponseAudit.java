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

  String code;
  String message;
  Date date;
}
