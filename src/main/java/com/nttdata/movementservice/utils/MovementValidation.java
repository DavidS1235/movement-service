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
<<<<<<< HEAD
    private String code;
    private TypeValidationResult result;
    private Number remainder;
    private Boolean updateRemainder;

    public void MovementValidation(){
        code = "";
        result = TypeValidationResult.UNASIGNED;
        updateRemainder = false;
        remainder = -1;
    }
=======
  private TypeValidationResult result;
  private Number remainder;
>>>>>>> 5a3f7c8451b9337193a9360f475831469400568d
}
