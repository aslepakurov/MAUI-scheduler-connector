package ua.kpi.comsys.maui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.domain.Request;
import ua.kpi.comsys.maui.dao.RequestDAO;

import java.util.Collection;

/**
 * RequestService Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Component
public class RequestService {
    @Autowired
    private RequestDAO dao;

    public boolean collectionExist(){return dao.collectionExist();}

    public Collection<Request> getRequests() {
        return dao.getRequests();
    }

    public Request getRequest(String id) {
        return dao.getRequest(id);
    }

    public void remove(String id) {dao.remove(id);}

    public void save(Request request) {
        dao.save(request);
    }
}
