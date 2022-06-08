package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.client.MovementClient;
import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.api.ResponseAudit;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService{

    final private static String CODE_OK = "0000";
    final private static String CODE_ERROR = "0001";
    final private static String CODE_EXCEPTION = "0002";
    final private static String CODE_NO_DATA = "0003";
    final private static String CODE_UNKNOWN = "0004";
    final private static String CODE_NO_EXIST = "0005";

    @Autowired
    private MovementRepository movementRepository;

    //@Autowired
    private WebClient movementClient;

    //@Autowired
    //private MovementClient movementClient;
    @Override
    public Mono<ResponseMovement> createMovement(RequestMovement request) {
        ResponseMovement response = new ResponseMovement();
        Movement movement;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            movement = movementRepository.createMovement(request);

            if(null != movement){
                if(!movement.getId().isEmpty()){
                    response.getAudit().setCode(CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(movement);
                }
                else{
                    response.getAudit().setCode(CODE_ERROR);
                }
            } else {
                response.getAudit().setCode(CODE_UNKNOWN);
            }
        } catch(Exception e){
            response.getAudit().setCode(CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }

    @Override
    public Mono<ResponseMovement> findAllMovement(RequestMovement request) {
        ResponseMovement response = new ResponseMovement();
        List<Movement> list;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            list = movementRepository.findAllMovement(request);

            if(null != list){
                if(0 < list.size()){
                    response.getAudit().setCode(CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().addAll(list);
                }
                else{
                    response.getAudit().setCode(CODE_NO_DATA);
                }
            } else{
                response.getAudit().setCode(CODE_UNKNOWN);
            }

        } catch(Exception e){
            response.getAudit().setCode(CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }

    @Override
    public Mono<ResponseMovement> updateMovement(RequestMovement request) {
        ResponseMovement response = new ResponseMovement();
        Movement movement;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            movement = movementRepository.updateMovement(request);

            if(null != movement){
                if(request.getId() != movement.getId()){
                    response.getAudit().setCode(CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(movement);
                }
                else{
                    response.getAudit().setCode(CODE_ERROR);
                }
            } else {
                response.getAudit().setCode(CODE_UNKNOWN);
            }
        } catch(Exception e){
            response.getAudit().setCode(CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }

    @Override
    public Mono<ResponseMovement> deleteMovement(RequestMovement request) {
        ResponseMovement response = new ResponseMovement();
        Movement movement;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        try{
            movement = movementRepository.deleteMovement(request);

            if(null != movement){
                if(request.getId() != movement.getId()){
                    response.getAudit().setCode(CODE_OK);
                    response.setList(new ArrayList<>());
                    response.getList().add(movement);
                }
                else{
                    response.getAudit().setCode(CODE_ERROR);
                }
            } else {
                response.getAudit().setCode(CODE_UNKNOWN);
            }
        } catch(Exception e){
            response.getAudit().setCode(CODE_EXCEPTION);
            response.getAudit().setMessage(e.getMessage());
        }

        return Mono.just(response);
    }

    @Override
    public Mono<ResponseMovement> callClient(RequestMovement request) {
        ResponseMovement response = new ResponseMovement();

        WebClient client = WebClient.builder().baseUrl("http://localhost:8006").build();

        return client
                .post()
                .uri("/findall")
                .body(BodyInserters.fromPublisher(Mono.just(request), RequestMovement.class))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseMovement.class);
    }
}
