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
@Document("SUB_TYPE_PRODUCT")
public class SubTypeProduct {

    @Id
    private String id;
    private String code;
    private String name;
    private Date date;
    //limite de movimientos mensuales
    private Number limitMount;
    //limite de movimientos diarios
    private Number limitDay;
    //limite de credito
    private Number limitCredit;
    //esta afecto a comision
    private Boolean commission;
    //dia especifico del mes para retiro o deposito
    private Number numDayMonth;
    //tipo de producto
    private TypeProduct typeProduct;

}
