package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.RequestTypeRule;
import com.nttdata.movementservice.model.entity.TypeMovement;
import com.nttdata.movementservice.model.entity.TypeRule;
import com.nttdata.movementservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de implementar la funcionalidad de la interfaz
 * para la comunicacion con la base de datos
 */
@Repository
class TypeRuleRepositoryImpl implements TypeRuleRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    /**
     * Este método se encarga de crear un registro en la base de datos
     * @param request objeto recibido de la api
     * @return TypeMovement objeto devuelto por la base de datos
     */
    public TypeRule createTypeRule(RequestTypeRule request) {
        request.setFgActive(true);

        return template.insert(Utils.RequestToTypeRule(request));
    }

    @Override
    /**
     * Este método se encarga de buscar registros en la base de datos
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return List<TypeMovement> lista de registros obtenidos
     */
    public List<TypeRule> findAllTypeRule(RequestTypeRule request) {
        return template.find(new Query(Criteria.where("fgActive").is(request.getFgActive())),
                TypeRule.class);
    }

    @Override
    /**
     * Este método se encarga de buscar un registro en la base de datos
     * @param request objeto recibido de la api con los filtros de busqueda
     * @return List<TypeMovement> lista de registros obtenidos
     */
    public List<TypeRule> findTypeRule(RequestTypeRule request) {
        List<TypeRule> list = new ArrayList<>();

        list = template.find(new Query(Criteria.where("id").is(request.getId())),
                TypeRule.class);

        return list;
    }

    @Override
    /**
     * Este método se encarga de buscar un registro en la base de datos
     * @param code codigo de registro
     * @return TypeMovement registros obtenido
     */
    public TypeRule findTypeRuleByCode(String code) {
        return template.find(new Query(Criteria.where("code").is(code)),
                TypeRule.class).get(0);
    }

    @Override
    /**
     * Este método se encarga de borrar logicamente un registro en la base de datos
     * @param request objeto recibido de la api
     * @return TypeMovement objeto devuelto por la base de datos
     */
    public TypeRule deleteTypeRule(RequestTypeRule request) {
        Query query = new Query(Criteria.where("id").is(request.getId()));
        Update update = new Update().set("fgActive", false);

        return template.findAndModify(query, update, TypeRule.class);
    }
}
