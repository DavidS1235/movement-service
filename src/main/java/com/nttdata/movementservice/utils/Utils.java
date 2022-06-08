package com.nttdata.movementservice.utils;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.entity.Movement;
import org.springframework.beans.BeanUtils;

public class Utils {
    public static RequestMovement movementToRequest(Movement movement){
        RequestMovement request = new RequestMovement();
        BeanUtils.copyProperties(movement, request);

        return request;
    }

    public static Movement RequestToMovement(RequestMovement request){
        Movement movement = new Movement();
        BeanUtils.copyProperties(request, movement);

        return movement;
    }

    public static ResponseMovement movementToResponse(Movement movement){
        ResponseMovement response = new ResponseMovement();
        BeanUtils.copyProperties(movement, response);

        return response;
    }

}
