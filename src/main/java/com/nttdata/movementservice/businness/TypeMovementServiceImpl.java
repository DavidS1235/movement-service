package com.nttdata.movementservice.businness;

import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.ResponseAudit;
import com.nttdata.movementservice.model.api.ResponseTypeMovement;
import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.repository.TypeMovementRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TypeMovementServiceImpl implements TypeMovementService {

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

  @Autowired
  private TypeMovementRepository typeMovementRepository;

  @Override
  /**
   * Este método se encarga de solicitar la creacion de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseTypeMovement> createTypeMovement(RequestTypeMovement request) {
    ResponseTypeMovement response = new ResponseTypeMovement();
    TypeMovement typeMovement;

    response.setAudit(new ResponseAudit());
    response.getAudit().setDate(new Date());

    try {
      typeMovement = typeMovementRepository.createTypeMovement(request);

      if (null != typeMovement) {
        if (!typeMovement.getId().isEmpty()) {
          response.getAudit().setCode(CODE_OK);
          response.setList(new ArrayList<>());
          response.getList().add(typeMovement);
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
   * Este método se encarga de solicitar la busqueda de registros
   * @param request objeto recibido de la api con los filtros de busqueda
   * @return Mono<ResponseTypeMovement> lista de registros obtenidos
   */
  public Mono<ResponseTypeMovement> findAllTypeMovement(RequestTypeMovement request) {
    ResponseTypeMovement response = new ResponseTypeMovement();
    List<TypeMovement> list;

    response.setAudit(new ResponseAudit());
    response.getAudit().setDate(new Date());

    try {
      list = typeMovementRepository.findAllTypeMovement(request);

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
   * Este método se encarga de solicitar la busqueda de un registro
   * @param request objeto recibido de la api con los filtros de busqueda
   * @return Mono<ResponseTypeMovement> lista de registros obtenidos
   */
  public Mono<ResponseTypeMovement> findTypeMovement(RequestTypeMovement request) {
    ResponseTypeMovement response = new ResponseTypeMovement();
    List<TypeMovement> list;

    response.setAudit(new ResponseAudit());
    response.getAudit().setDate(new Date());

    try {
      list = typeMovementRepository.findTypeMovement(request);

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
   * Este método se encarga de solicitar el borrado logico de un registro
   * @param request objeto recibido de la api
   * @return Mono<ResponseTypeMovement> objeto devuelto por la base de datos
   */
  public Mono<ResponseTypeMovement> deleteTypeMovement(RequestTypeMovement request) {
    ResponseTypeMovement response = new ResponseTypeMovement();
    TypeMovement typeMovement;

    response.setAudit(new ResponseAudit());
    response.getAudit().setDate(new Date());

    try {
      typeMovement = typeMovementRepository.deleteTypeMovement(request);

      if (null != typeMovement) {
        if (request.getId() != typeMovement.getId()) {
          response.getAudit().setCode(CODE_OK);
          response.setList(new ArrayList<>());
          response.getList().add(typeMovement);
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

}
