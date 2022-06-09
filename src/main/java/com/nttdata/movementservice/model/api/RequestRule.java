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
 * Esta clase implementa un requerimiento de regla de la api
 */
public class RequestRule {
    private String id;
    private String idSubTypeProduct;
    private String idTypeRule;
    private Number numAmount;
    private Boolean fgActive;

}
