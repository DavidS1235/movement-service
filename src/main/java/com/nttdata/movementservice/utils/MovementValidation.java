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
}
