package com.nttdata.movementservice.utils;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa una respuesta de validacion de movimiento
 */
public class MovementValidation {
  private TypeValidationResult result;
  private Number remainder;
}
