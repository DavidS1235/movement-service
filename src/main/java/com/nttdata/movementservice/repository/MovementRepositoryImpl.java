package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se encarga de implementar la funcionalidad de la interfaz
 * para la comunicacion con la base de datos
 */
@Repository
class MovementRepositoryImpl implements MovementRepository {

  @Autowired
  private MongoTemplate template;

  @Override
  /**
   * Este método se encarga de crear un registro en la base de datos
   * @param request objeto recibido de la api
   * @return Movement objeto devuelto por la base de datos
   */
  public Movement createMovement(RequestMovement request) {
    request.setFecMovement(new Date());
    request.setFgActive(true);

    return template.insert(Utils.RequestToMovement(request));
  }

  @Override
  /**
   * Este método se encarga de buscar registros en la base de datos
   * @param request objeto recibido de la api con los filtros de busqueda
   * @return List<Movement> lista de registros obtenidos
   */
  public List<Movement> findAllMovement(RequestMovement request) {
    List<Movement> list = new ArrayList<>();

    if (request.getIdTypeMovement().equals("4")) {
      list = template.find(new Query(Criteria.where("idProduct").is(request.getIdProduct())),
              Movement.class);
    } else {
      list = template.find(new Query(Criteria.where("idProduct").is(request.getIdProduct()))
                      .addCriteria(Criteria.where("idTypeMovement").is(request.getIdTypeMovement())),
              Movement.class);
    }

    return list;
  }

  @Override
  /**
   * Este método se encarga de actualizar un registro en la base de datos
   * @param request objeto recibido de la api
   * @return Movement objeto devuelto por la base de datos
   */
  public Movement updateMovement(RequestMovement request) {
    Query query = new Query(Criteria.where("id").is(request.getId()));
    Update update = new Update().set("numAmount", request.getNumAmount())
            .set("idTypeMovement", request.getIdTypeMovement());

    return template.findAndModify(query, update, Movement.class);
  }

  @Override
  /**
   * Este método se encarga de borrar logicamente un registro en la base de datos
   * @param request objeto recibido de la api
   * @return Movement objeto devuelto por la base de datos
   */
  public Movement deleteMovement(RequestMovement request) {
    Query query = new Query(Criteria.where("id").is(request.getId()));
    Update update = new Update().set("fgActive", false);

    return template.findAndModify(query, update, Movement.class);
  }

  @Override
  /**
   * Este método se encarga de buscar registros en la base de datos
   * @param idProduct id del producto
   * @param dayMonth dia del mes
   * @param idsTypeMovement ids de tipo, separados por coma
   * @return List<Movement> lista de registros obtenidos
   */
  public List<Movement> getListMovementDay(String idProduct, Number dayMonth, String idsTypeMovement) {
    List<String> ids = new ArrayList<String>(Arrays.asList(idsTypeMovement.split(",")));

    Date startDate = Utils.getFirstDayCurrentMonth();
    Date endDate = Utils.getLastDayCurrentMonth();

    List<Movement> listOriginal = template.find(new Query(Criteria.where("idProduct").is(idProduct)
                    .andOperator(Criteria.where("fgActive").is(true))
                    .andOperator(Criteria.where("fecMovement").gte(startDate).lt(endDate))
                    .andOperator(Criteria.where("idTypeMovement").in(ids))
            ),
            Movement.class);

    List<Movement> listFinal = new ArrayList<>();
    for (Movement element : listOriginal) {
      if (Utils.isDayMonth(element.getFecMovement(), dayMonth.intValue())) {
        listFinal.add(element);
      }
    }

    return listFinal;
  }

  @Override
  /**
   * Este método se encarga de buscar registros en la base de datos
   * @param idProduct id del producto
   * @param idsTypeMovement ids de tipo, separados por coma
   * @return List<Movement> lista de registros obtenidos
   */
  public List<Movement> getListMovementMonth(String idProduct, String idsTypeMovement) {
    List<String> ids = new ArrayList<String>(Arrays.asList(idsTypeMovement.split(",")));

    Date startDate = Utils.getFirstDayCurrentMonth();
    Date endDate = Utils.getLastDayCurrentMonth();

    return template.find(new Query(Criteria.where("idProduct").is(idProduct)
                    .andOperator(Criteria.where("fgActive").is(true))
                    .andOperator(Criteria.where("fecMovement").gte(startDate).lt(endDate))
                    .andOperator(Criteria.where("idTypeMovement").in(ids))
            ),
            Movement.class);
  }
}
