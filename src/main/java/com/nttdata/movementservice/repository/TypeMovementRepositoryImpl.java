package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Esta clase se encarga de implementar la funcionalidad de la interfaz
 * para la comunicacion con la base de datos
 */
@Repository
class TypeMovementRepositoryImpl implements TypeMovementRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    /**
     * Este método se encarga de crear un registro en la base de datos
     * @param request objeto recibido de la api
     * @return TypeMovement objeto devuelto por la base de datos
     */
    public TypeMovement createTypeMovement(RequestTypeMovement request) {
        request.setFgActive(true);

        return template.insert(Utils.RequestToTypeMovement(request));
    }

    @Override
    /**
     * Este método se encarga de buscar registros en la base de datos
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return List<TypeMovement> lista de registros obtenidos
     */
    public List<TypeMovement> findAllTypeMovement(RequestTypeMovement request) {
        return template.find(new Query(Criteria.where("fgActive").is(request.getFgActive())),
                TypeMovement.class);
    }

    @Override
    /**
     * Este método se encarga de buscar un registro en la base de datos
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return List<TypeMovement> lista de registros obtenidos
     */
    public List<TypeMovement> findTypeMovement(RequestTypeMovement request) {
        List<TypeMovement> list = new ArrayList<>();

        list = template.find(new Query(Criteria.where("id").is(request.getId())),
                TypeMovement.class);

        return list;
    }

    @Override
    /**
     * Este método se encarga de buscar un registro en la base de datos
     * @param code codigo de registro
     * @return TypeMovement registros obtenido
     */
    public TypeMovement findTypeMovementByCode(String code) {
        return template.find(new Query(Criteria.where("code").is(code)),
                TypeMovement.class).get(0);
    }

    @Override
    /**
     * Este método se encarga de borrar logicamente un registro en la base de datos
     * @param request objeto recibido de la api
     * @return TypeMovement objeto devuelto por la base de datos
     */
    public TypeMovement deleteTypeMovement(RequestTypeMovement request) {
        Query query = new Query(Criteria.where("id").is(request.getId()));
        Update update = new Update().set("fgActive", false);

        return template.findAndModify(query, update, TypeMovement.class);
    }
}
