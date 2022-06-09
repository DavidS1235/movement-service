package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.model.entity.TypeRule;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa una respuesta de tipo de reglas de la api
 */
public class ResponseTypeRule {

    ResponseAudit audit;
    List<TypeRule> list;

    public void ResponseTypeRule(){
        setAudit(new ResponseAudit());
    }
}
