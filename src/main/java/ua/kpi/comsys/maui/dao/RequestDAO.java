package ua.kpi.comsys.maui.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.domain.Request;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * RequestDAO Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Component
public class RequestDAO {
    private static Logger logger = Logger.getLogger(RequestDAO.class.getName());
    @Autowired
    private MongoOperations mongoTemplate;

    public Collection<Request> getRequests() {
        List<Request> requests = mongoTemplate.findAll(Request.class);
        logger.info(requests.size() + " requests found!");
        return requests;
    }

    public Request getRequest(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Request.class);
    }

    public void remove(String id) {
        mongoTemplate.remove(mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Request.class));
    }

    public void save(Request request) {
        if(!collectionExist()){
            logger.info("Created collection!");
            mongoTemplate.createCollection(Request.class);
        }
        mongoTemplate.save(request);
        logger.info("Request with id=" + request.getId() + " saved!");
    }

    public boolean collectionExist(){
        return mongoTemplate.collectionExists(Request.class);
    }
}
