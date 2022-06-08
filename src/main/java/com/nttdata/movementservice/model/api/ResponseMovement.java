package com.nttdata.movementservice.model.api;

import com.nttdata.movementservice.model.entity.Movement;
import lombok.*;

import java.util.List;

//@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMovement {

    ResponseAudit audit;
    List<Movement> list;
}
