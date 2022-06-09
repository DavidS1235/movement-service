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
@Document(collection = "PRODUCT")
public class Product {

    @Id
    private String id;
    private String idBank;
    private String tpeCrrency;
    private Number numRemainder;

    private Date date;
    private SubTypeProduct subTypeProduct;
    private String idClient;

}
