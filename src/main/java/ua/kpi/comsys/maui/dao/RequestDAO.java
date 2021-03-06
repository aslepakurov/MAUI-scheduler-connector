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
    private static final Logger LOGGER = Logger.getLogger(RequestDAO.class.getName());
    @Autowired
    private MongoOperations mongoTemplate;

    public Request getRequest(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Request.class);
    }

    public Collection<Request> getRequests() {
        Query query = new Query(Criteria.where("id").exists(true));
        List<Request> requests = mongoTemplate.find(query, Request.class);
        LOGGER.info(requests.size() + " requests found!");
        return requests;
    }

    public Collection<Request> getRequests(Query query) {
        query.fields().include("id").include("name").include("priority").include("description")
                .include("user_id").include("creation_date").include("status").include("cpu").include("ram")
                    .include("node").include("walltime").include("storage").include("input_arguments").include("bash")
                        .include("hook_start").include("hook_end");
        List<Request> requests = mongoTemplate.find(query, Request.class);
        LOGGER.info(requests.size() + " requests found!");
        return requests;
    }

    public void remove(String id) {
        if (id.equals("all")) {
            mongoTemplate.remove(Query.query(Criteria.where("id").exists(true)), Request.class);
            LOGGER.info("Removed everything :3");
        } else {
            mongoTemplate.remove(mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Request.class));
            LOGGER.info("Request with id = " + id + " removed!");
        }
    }

    //TODO: refactoring
    public void save(Request request) {
        if (!collectionExist()) {
            LOGGER.info("Created collection!");
            mongoTemplate.createCollection(Request.class);
        }
        Request existingRequest = getRequest(request.getId());
        if (existingRequest != null) {
            mongoTemplate.remove(existingRequest);
        }
        mongoTemplate.save(request);
        LOGGER.info("Request: " + request);
    }

    public boolean requestExists(String id) {
        Request request = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Request.class);
        return request != null;
    }

    public boolean collectionExist() {
        return mongoTemplate.collectionExists(Request.class);
    }

    /**
     * For tests
     */
    void setMongoTemplate(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
