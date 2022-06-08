package com.nttdata.movementservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PRODUCT")
public class Product {

    @Id
    private Integer id;
    private Integer idBank;
    private Integer idClient;
    private Integer idStpePduct;
    private Integer tpeCrrcy;
    private Integer idSubTypeCurrency;
    private Integer idTypeCurrency;
    private Integer idClient1;
    private Integer idBank1;

}
