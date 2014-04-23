package ua.kpi.comsys.maui.dao;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.domain.parameters.ParameterKind;
import ua.kpi.comsys.maui.domain.Parameters;

import java.util.logging.Logger;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 24/04/14.
 */
@Component
public class ParamDAO {
    private static Logger LOG = Logger.getLogger(ParamDAO.class.getName());
    @Autowired
    private MongoOperations mongoTemplate;

    public Parameters getParameters(ParameterKind kind) {
        Parameters parameters = mongoTemplate.findOne(new Query(Criteria.where("kind").is(kind)), Parameters.class);
        if (parameters != null) {
            LOG.info("Parameters found - "+parameters.toString());
            return parameters;
        }
        return null;
    }

    public void save(Parameters parameters) {
        if(!collectionExist()){
            LOG.info("Created collection!");
            mongoTemplate.createCollection(Parameters.class);
        }
        mongoTemplate.save(parameters);
        LOG.info("Request with id="+parameters.getKind()+" saved!");
    }

    public void save(BasicDBObject parameters) {
        if(!collectionExist()){
            LOG.info("Created collection!");
            mongoTemplate.createCollection(Parameters.class);
        }
        mongoTemplate.save(parameters, "parameters");
    }

    public boolean collectionExist(){
        return mongoTemplate.collectionExists(Parameters.class);
    }
}
