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
@Document(collection = "RULE")
/**
 * Esta clase implementa un registro regla en la base de datos
 */
public class Rule {
    @Id
    private String id;
    private String idSubTypeProduct;
    private String idTypeRule;
    private Number numAmount;
    private Boolean fgActive;
}
