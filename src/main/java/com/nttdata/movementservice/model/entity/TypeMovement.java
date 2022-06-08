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
@Document(collection = "TYPE_MOVEMENT")
public class TypeMovement {
    @Id
    private Integer id;
    private String code;
    private String name;
}
