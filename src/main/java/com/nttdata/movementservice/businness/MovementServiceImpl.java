package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.ResponseAudit;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.entity.Product;
import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.repository.MovementRepository;
import com.nttdata.movementservice.repository.TypeMovementRepository;
import com.nttdata.movementservice.utils.MovementValidation;
import com.nttdata.movementservice.utils.TypeValidationResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovementServiceImpl implements MovementService {

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
  final private static String CODE_ERROR_PRODUCT_REMAINDER = "0007";
  //No se ha superado la validacion de limites de movimientos
  final private static String CODE_ERROR_LIMIT = "0008";
  //No se ha superado la validacion de tipo de movimientos
  final private static String CODE_ERROR_TYPE_MOVEMENT = "0009";
  //Saldo insuficiente
  final private static String CODE_ERROR_INSUFFICIENT_BALANCE = "0010";

  @Autowired
  private MovementRepository movementRepository;

  @Autowired
  private TypeMovementRepository typeMovementRepository;

  //private WebClient movementClient;

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

    try {
      Product product = getProduct(request.getIdProduct());
      TypeMovement typeMovement = getTypeMovement(request.getIdTypeMovement());

      MovementValidation validation = validate(product, request, typeMovement);

      if (validation.getResult().equals(TypeValidationResult.OK)) {
        if (product.getNumRemainder() != validation.getRemainder()) {
          movement = movementRepository.createMovement(request);

          product = updateProductRemainder(product.getId(), validation.getRemainder());

          response.getAudit().setCode(CODE_OK);
          response.setList(new ArrayList<>());
          response.getList().add(movement);
        } else {
          response.getAudit().setCode(CODE_ERROR_PRODUCT_REMAINDER);
        }
      } else {
        if (validation.getResult().equals(TypeValidationResult.ERROR_TYPE_MOVEMENT)) {
          response.getAudit().setCode(CODE_ERROR_TYPE_MOVEMENT);
        } else if (validation.getResult().equals(TypeValidationResult.ERROR_INSUFFICIENT_BALANCE)) {
          response.getAudit().setCode(CODE_ERROR_INSUFFICIENT_BALANCE);
        } else if (validation.getResult().equals(TypeValidationResult.ERROR_LIMIT)) {
          response.getAudit().setCode(CODE_ERROR_LIMIT);
        }
      }
    } catch (Exception e) {
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

    try {
      list = movementRepository.findAllMovement(request);

      if (null != list) {
        if (0 < list.size()) {
          response.getAudit().setCode(CODE_OK);
          response.setList(new ArrayList<>());
          response.getList().addAll(list);
        } else {
          response.getAudit().setCode(CODE_NO_DATA);
        }
      } else {
        response.getAudit().setCode(CODE_UNKNOWN);
      }

    } catch (Exception e) {
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

    try {
      movement = movementRepository.updateMovement(request);

      if (null != movement) {
        if (request.getId() != movement.getId()) {
          response.getAudit().setCode(CODE_OK);
          response.setList(new ArrayList<>());
          response.getList().add(movement);
        } else {
          response.getAudit().setCode(CODE_ERROR);
        }
      } else {
        response.getAudit().setCode(CODE_UNKNOWN);
      }
    } catch (Exception e) {
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

    try {
      movement = movementRepository.deleteMovement(request);

      if (null != movement) {
        if (request.getId() != movement.getId()) {
          response.getAudit().setCode(CODE_OK);
          response.setList(new ArrayList<>());
          response.getList().add(movement);
        } else {
          response.getAudit().setCode(CODE_ERROR);
        }
      } else {
        response.getAudit().setCode(CODE_UNKNOWN);
      }
    } catch (Exception e) {
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
   * Este método se encarga de validar reglas de negocio para el registro de movimientos
   *
   * @param product         producto
   * @param requestMovement movimiento a validar
   * @param typeMovement    tipo de movimiento del movimiento
   * @return MovementValidation resultado de la validacion
   */
  private MovementValidation validate(Product product, RequestMovement requestMovement, TypeMovement typeMovement) {
    MovementValidation validation = new MovementValidation();

    TypeMovement typeMovementAbono = getTypeMovementByCode("00001");
    TypeMovement typeMovementRetiro = getTypeMovementByCode("00002");

    switch (product.getSubTypeProduct().getCode()) {
      case "00001":
        List<Movement> listMovementMonth = getListMovementMonth(product.getId(), typeMovementAbono.getId().concat(",").concat(typeMovementRetiro.getId()));
        if (listMovementMonth.size() < product.getSubTypeProduct().getLimitMount().intValue()) {
          switch (typeMovement.getCode()) {
            case "00001":
              validation.setResult(TypeValidationResult.OK);
              validation.setRemainder(product.getNumRemainder().doubleValue() + requestMovement.getNumAmount().doubleValue());
              break;
            case "00002":
              if (0 <= product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue()) {
                validation.setResult(TypeValidationResult.OK);
              } else {
                validation.setResult(TypeValidationResult.ERROR_INSUFFICIENT_BALANCE);
              }

              validation.setRemainder(product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue());
              break;
            default:
              validation.setResult(TypeValidationResult.ERROR_TYPE_MOVEMENT);
          }
        } else {
          validation.setResult(TypeValidationResult.ERROR_LIMIT);
        }

        break;
      case "00002":
        switch (typeMovement.getCode()) {
          case "00001":
            validation.setResult(TypeValidationResult.OK);
            validation.setRemainder(product.getNumRemainder().doubleValue() + requestMovement.getNumAmount().doubleValue());
            break;
          case "00002":
            if (0 <= product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue()) {
              validation.setResult(TypeValidationResult.OK);
            } else {
              validation.setResult(TypeValidationResult.ERROR_INSUFFICIENT_BALANCE);
            }

            validation.setRemainder(product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue());
            break;
          default:
            validation.setResult(TypeValidationResult.ERROR_TYPE_MOVEMENT);
        }

        break;
      case "00003":
        List<Movement> listMovementDay = getListMovementDay(product.getId(), product.getSubTypeProduct().getNumDayMonth(), typeMovementAbono.getId().concat(",").concat(typeMovementRetiro.getId()));
        if (listMovementDay.size() < product.getSubTypeProduct().getLimitDay().intValue()) {
          switch (typeMovement.getCode()) {
            case "00001":
              validation.setResult(TypeValidationResult.OK);
              validation.setRemainder(product.getNumRemainder().doubleValue() + requestMovement.getNumAmount().doubleValue());
              break;
            case "00002":
              if (0 <= product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue()) {
                validation.setResult(TypeValidationResult.OK);
              } else {
                validation.setResult(TypeValidationResult.ERROR_INSUFFICIENT_BALANCE);
              }

              validation.setRemainder(product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue());
              break;
            default:
              validation.setResult(TypeValidationResult.ERROR_TYPE_MOVEMENT);
          }
        } else {
          validation.setResult(TypeValidationResult.ERROR_LIMIT);
        }

        break;
      case "00004":
        switch (typeMovement.getCode()) {
          case "00005":
            validation.setResult(TypeValidationResult.OK);
            validation.setRemainder(product.getNumRemainder().doubleValue() + requestMovement.getNumAmount().doubleValue());
            break;
          default:
            validation.setResult(TypeValidationResult.ERROR_TYPE_MOVEMENT);
        }

        break;
      case "00005":
        switch (typeMovement.getCode()) {
          case "00005":
            validation.setResult(TypeValidationResult.OK);
            validation.setRemainder(product.getNumRemainder().doubleValue() + requestMovement.getNumAmount().doubleValue());
            break;
          default:
            validation.setResult(TypeValidationResult.ERROR_TYPE_MOVEMENT);
        }

        break;
      case "00006":
        switch (typeMovement.getCode()) {
          case "00005":
            validation.setResult(TypeValidationResult.OK);
            validation.setRemainder(product.getNumRemainder().doubleValue() + requestMovement.getNumAmount().doubleValue());
            break;
          case "00006":
            if (0 <= product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue()) {
              validation.setResult(TypeValidationResult.OK);
            } else {
              validation.setResult(TypeValidationResult.ERROR_INSUFFICIENT_BALANCE);
            }

            validation.setRemainder(product.getNumRemainder().doubleValue() - requestMovement.getNumAmount().doubleValue());
            break;
          default:
            validation.setResult(TypeValidationResult.ERROR_TYPE_MOVEMENT);
        }

        break;
    }

    return validation;
  }

  /**
   * Este método se encarga de obtener una lista de movimientos de un producto
   *
   * @param idProduct         id del producto
   * @param dayMonth          dia del mes
   * @param codesTypeMovement lista de codigos de tipo de movimiento buscados, separados por comas
   * @return List<Movement> lista de movimientos encontrados
   */
  List<Movement> getListMovementDay(String idProduct, Number dayMonth, String codesTypeMovement) {
    return null;
  }

  /**
   * Este método se encarga de obtener una lista de movimientos de un producto
   *
   * @param idProduct         id del producto
   * @param codesTypeMovement lista de codigos de tipo de movimiento buscados, separados por comas
   * @return List<Movement> lista de movimientos encontrados
   */
  List<Movement> getListMovementMonth(String idProduct, String codesTypeMovement) {
    return null;
  }

  /**
   * Este método se encarga de obtener un producto buscado por id
   *
   * @param idProduct id del producto buscado
   * @return Product objeto devuelto
   */
  private Product getProduct(String idProduct) {
    WebClient client = WebClient.builder().baseUrl("http://localhost:8005").build();
    Mono<Product> product = client
            .get()
            .uri("/api/product", "{ \"id\":" + idProduct + "}")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Product.class);

    return product.block();
  }

  /**
   * Este método se encarga de actualizar el saldo de un producto
   *
   * @param idProduct    id del producto buscado
   * @param numRemainder nuevo saldo
   * @return Product objeto devuelto
   */
  private Product updateProductRemainder(String idProduct, Number numRemainder) {
    Product productUpdate = new Product();
    productUpdate.setId(idProduct);
    productUpdate.setNumRemainder(numRemainder);

    WebClient client = WebClient.builder().baseUrl("http://localhost:8005").build();

    Mono<Product> product = client
            .post()
            .uri("/api/product/balance")
            .body(BodyInserters.fromPublisher(Mono.just(productUpdate), Product.class))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Product.class);

    return product.block();
  }

  /**
   * Este método se encarga de obtener un tipo de movimiento buscado por id
   *
   * @param idTypeMovement id del tipo de movimiento buscado
   * @return TypeMovement objeto devuelto
   */
  private TypeMovement getTypeMovement(String idTypeMovement) {
    RequestTypeMovement requestTypeMovementInput = new RequestTypeMovement();
    requestTypeMovementInput.setId(idTypeMovement);

    return typeMovementRepository.findTypeMovement(requestTypeMovementInput).get(0);
  }

  /**
   * Este método se encarga de obtener un tipo de movimiento buscado por code
   *
   * @param code code del tipo de movimiento buscado
   * @return TypeMovement objeto devuelto
   */
  private TypeMovement getTypeMovementByCode(String code) {
    RequestTypeMovement requestTypeMovementInput = new RequestTypeMovement();
    requestTypeMovementInput.setCode(code);

    return typeMovementRepository.findTypeMovementByCode(code);
  }

}
