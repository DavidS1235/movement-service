package com.nttdata.movementservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("TYPE_RULE")
public class TypeRule {

    @Id
    private String id;
    private String code;
    private String name;
    private Boolean fgActive;

}
