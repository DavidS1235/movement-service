package com.nttdata.movementservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movement")
/**
 * Esta clase implementa un registro movimiento en la base de datos
 */
public class Movement {
    @Id
    private String id;
    private String idProduct;
    private Date fecMovement;
    private String idTypeMovement;
    private Number numAmount;
    private Boolean fgActive;
}
