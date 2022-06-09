package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.api.ResponseAudit;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.entity.Product;
import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.repository.MovementRepository;
import com.nttdata.movementservice.repository.TypeMovementRepository;
import com.nttdata.movementservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService{

    //Todo ok
    final private static String CODE_OK = "0000";
    //Se encontro un error generico
    final private static String CODE_ERROR = "0001";
    //Se encontro una excepcion
    final private static String CODE_EXCEPTION = "0002";
    //No se obtuvieron datos
    final private static String CODE_NO_DATA = "0003";
    //Error desconocido
    final private static String CODE_UNKNOWN = "0004";
    //El objeto no existe
    final private static String CODE_NO_EXIST = "0005";
    //No se pudo comunicar con el servicio de producto
    final private static String CODE_PRODUCT = "0006";
    //No se actualiza el saldo de producto
    final private static String CODE_PRODUCT_REMAINDER = "0007";

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private TypeMovementRepository typeMovementRepository;

    private WebClient movementClient;

    @Override
    /**
     * Este método se encarga de solicitar la creacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
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

                    Mono<ResponseMovement> productUpdate = updateRemainder(request);
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
    /**
     * Este método se encarga de solicitar la busqueda de registros
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return Mono<ResponseMovement> lista de registros obtenidos
     */
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
    /**
     * Este método se encarga de solicitar la actualizacion de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
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
    /**
     * Este método se encarga de solicitar el borrado logico de un registro
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
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
    /**
     * Este método se encarga de consumir un servicio externo
     * Este ejemplo consume al metodo api findall del mismo
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
    public Mono<ResponseMovement> callClient(RequestMovement request) {
        WebClient client = WebClient.builder().baseUrl("http://localhost:8006").build();

        return client
                .post()
                .uri("/findall")
                .body(BodyInserters.fromPublisher(Mono.just(request), RequestMovement.class))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseMovement.class);
    }

    /**
     * Este método se encarga de consumir un servicio externo
     * Este metodo consume al metodo api updateRemainder de product-service
     * @param request objeto recibido de la api
     * @return Mono<ResponseMovement> objeto devuelto por la base de datos
     */
    private Mono<ResponseMovement> updateRemainder(RequestMovement request) {
        ResponseMovement response = new ResponseMovement();
        Number remainder = 0;
        Movement movement;

        response.setAudit(new ResponseAudit());
        response.getAudit().setDate(new Date());

        //-obtener el saldo del producto
        //-obtener todos los movimeintos del producto
        //analizar el tipo de movimiento para saber si sumar o restar a ese valor
        //llamar a product-service para que actualice nuevo saldo
        //o devuelva el codigo de error indicando la regla de negocio violada

        WebClient client = WebClient.builder().baseUrl("http://localhost:8005").build();
        Mono<Product> product = client
                .get()
                .uri("/api/product", "{ \"id\":" + request.getIdProduct() + "}")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);

        if(null !=  product)
        {
            //remainderOriginal = product.block().getNumRemainder();
            RequestTypeMovement requestTypeMovementInput = new RequestTypeMovement();
            requestTypeMovementInput.setId(request.getIdTypeMovement());
            String codeTypeMovementInput = typeMovementRepository.findTypeMovement(requestTypeMovementInput).get(0).getCode();

            Mono<ResponseMovement> movementExist = findAllMovement(request);

            if(movementExist.block().getAudit().getCode().equals(CODE_OK) || movementExist.block().getAudit().getCode().equals(CODE_NO_DATA)) {

                //Boolean proceed = false;
                Integer numMovements = 0;

                switch (product.block().getSubTypeProduct().getCode()){
                    case "00001": //SI LIMITE MAX MOV MES - NO COMISION - ahorro
                        for(Movement movementElement: movementExist.block().getList()){
                            if(movementElement.getFgActive()) {
                                if(Utils.isCurrentMonth(movementElement.getFecMovement())) {
                                    numMovements++;
                                }
                            }
                        }

                        if(numMovements < product.block().getSubTypeProduct().getLimitMount().intValue()) {
                            switch (codeTypeMovementInput){
                                case "00001":
                                    remainder = product.block().getNumRemainder().doubleValue() + request.getNumAmount().doubleValue();
                                    break;
                                case "00002":
                                    remainder = (product.block().getNumRemainder().doubleValue() - request.getNumAmount().doubleValue() > 0)?
                                            product.block().getNumRemainder().doubleValue() - remainder.doubleValue():
                                            product.block().getNumRemainder().doubleValue();
                                    break;
                                default:
                                    remainder = product.block().getNumRemainder();
                            }
                        }

                        break;
                    case "00002": //NO LIMITE MAX MOV MES - SI COMISION - cuenta corriente
                        switch (codeTypeMovementInput){
                            case "00001":
                                remainder = product.block().getNumRemainder().doubleValue() + request.getNumAmount().doubleValue();
                                break;
                            case "00002":
                                remainder = (product.block().getNumRemainder().doubleValue() - request.getNumAmount().doubleValue() > 0)?
                                        product.block().getNumRemainder().doubleValue() - remainder.doubleValue():
                                        product.block().getNumRemainder().doubleValue();
                                break;
                            default:
                                remainder = product.block().getNumRemainder();
                        }

                        break;
                    case "00003": //SI LIMITE 1 MOV DIA ESPECIFICO (retiro o deposito) - NO COMISION - plazo fijo
                        for(Movement movementElement: movementExist.block().getList()){
                            if(movementElement.getFgActive()) {
                                if(Utils.isDayMonth(movementElement.getFecMovement(), product.block().getSubTypeProduct().getNumDayMonth().intValue())) {
                                    RequestTypeMovement requestTypeMovement = new RequestTypeMovement();
                                    requestTypeMovement.setId(movementElement.getIdTypeMovement());

                                    String codeTypeMovement = typeMovementRepository.findTypeMovement(requestTypeMovement).get(0).getCode();

                                    if(codeTypeMovement.equals("00001") || codeTypeMovement.equals("00002")) {
                                        numMovements++;
                                    }
                                }
                            }
                        }

                        if(numMovements < product.block().getSubTypeProduct().getLimitDay().intValue()) {
                            switch (codeTypeMovementInput){
                                case "00001":
                                    remainder = product.block().getNumRemainder().doubleValue() + request.getNumAmount().doubleValue();
                                    break;
                                case "00002":
                                    remainder = (product.block().getNumRemainder().doubleValue() - request.getNumAmount().doubleValue() > 0)?
                                            product.block().getNumRemainder().doubleValue() - remainder.doubleValue():
                                            product.block().getNumRemainder().doubleValue();
                                    break;
                                default:
                                    remainder = product.block().getNumRemainder();
                            }
                        }

                        break;
                    case "00004": //NO LIMITE MAX MOV - NO COMISION - personal
                        switch (codeTypeMovementInput){
                            case "00001":
                                remainder = product.block().getNumRemainder().doubleValue() + request.getNumAmount().doubleValue();
                                break;
                            case "00002":
                                remainder = (product.block().getNumRemainder().doubleValue() - request.getNumAmount().doubleValue() > 0)?
                                        product.block().getNumRemainder().doubleValue() - remainder.doubleValue():
                                        product.block().getNumRemainder().doubleValue();
                                break;
                            default:
                                remainder = product.block().getNumRemainder();
                        }
                        break;
                    case "00005": //NO LIMITE MAX MOV - NO COMISION - empresarial
                        switch (codeTypeMovementInput){
                            case "00001":
                                remainder = product.block().getNumRemainder().doubleValue() + request.getNumAmount().doubleValue();
                                break;
                            case "00002":
                                remainder = (product.block().getNumRemainder().doubleValue() - request.getNumAmount().doubleValue() > 0)?
                                        product.block().getNumRemainder().doubleValue() - remainder.doubleValue():
                                        product.block().getNumRemainder().doubleValue();
                                break;
                            default:
                                remainder = product.block().getNumRemainder();
                        }
                        break;
                    case "00006": //NO LIMITE MAX MOV - NO COMISION - tarjeta credito
                        switch (codeTypeMovementInput){
                            case "00001":
                                remainder = product.block().getNumRemainder().doubleValue() + request.getNumAmount().doubleValue();
                                break;
                            case "00002":
                                remainder = (product.block().getNumRemainder().doubleValue() - request.getNumAmount().doubleValue() > 0)?
                                        product.block().getNumRemainder().doubleValue() - remainder.doubleValue():
                                        product.block().getNumRemainder().doubleValue();
                                break;
                            default:
                                remainder = product.block().getNumRemainder();
                        }
                        break;
                    default:
                }

                if(remainder != product.block().getNumRemainder()) {

                    Product productUpdate = new Product();
                    productUpdate.setId(product.block().getId());
                    productUpdate.setNumRemainder(remainder);

                    client
                            .post()
                            .uri("/api/product/balance")
                            .body(BodyInserters.fromPublisher(Mono.just(productUpdate), Product.class))
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(Product.class);

                    response.getAudit().setCode(CODE_OK);
                } else {
                    response.getAudit().setCode(CODE_PRODUCT_REMAINDER);
                }

            } else {
                response.getAudit().setCode(CODE_UNKNOWN);
            }
        } else {
            response.getAudit().setCode(CODE_PRODUCT);
        }

        return Mono.just(response);
    }
}
