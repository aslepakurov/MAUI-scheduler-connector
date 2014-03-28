package ua.kpi.comsys.maui.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.bean.Request;

import java.util.Collection;
import java.util.List;

/**
 * RequestDAO Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Component
public class RequestDAO {
    private static final transient Log LOG = LogFactory.getLog(RequestDAO.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    public Collection<Request> getRequests() {
        List<Request> requests = mongoTemplate.findAll(Request.class);
        return requests;
    }

    public Request getRequest(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Request.class);
    }

    public void save(Request request) {
        mongoTemplate.save(request);
    }
}
