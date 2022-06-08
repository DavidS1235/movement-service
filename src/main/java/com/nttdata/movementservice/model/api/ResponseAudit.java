package com.nttdata.movementservice.model.api;

import lombok.*;

import java.util.Date;


//@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAudit {

    String code;
    String message;
    Date date;
}
