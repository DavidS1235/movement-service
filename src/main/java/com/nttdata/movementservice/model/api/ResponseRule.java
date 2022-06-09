package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.entity.Rule;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Esta clase implementa una respuesta de regla de la api
 */
public class ResponseRule {

    ResponseAudit audit;
    List<Rule> list;

    public void ResponseRule(){
        setAudit(new ResponseAudit());
    }
}
