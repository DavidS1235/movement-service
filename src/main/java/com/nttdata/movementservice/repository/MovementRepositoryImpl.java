package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.StreamUtils;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        if(request.getIdTypeMovement().equals("4")){
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
}
