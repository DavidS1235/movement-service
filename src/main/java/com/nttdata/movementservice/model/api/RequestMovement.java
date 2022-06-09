package com.nttdata.movementservice.model.api;

import lombok.*;

import java.util.Date;

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
public class RequestMovement {
    private String id;
    private String idProduct;
    private Date fecMovement;
    private String idTypeMovement;
    private Number numAmount;
    private Boolean fgActive;

}
