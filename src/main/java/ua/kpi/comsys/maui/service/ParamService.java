package ua.kpi.comsys.maui.service;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.dao.ParamDAO;
import ua.kpi.comsys.maui.domain.parameters.ParameterKind;
import ua.kpi.comsys.maui.domain.Parameters;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 24/04/14.
 */
@Component
public class ParamService {
    @Autowired
    private ParamDAO dao;

    public boolean collectionExist(){
        return dao.collectionExist();
    }

    public void save(Parameters param) {
        dao.save(param);
    }
    public void save(BasicDBObject param) {
        dao.save(param);
    }

    public Parameters getParam(ParameterKind kind) {
        return dao.getParameters(kind);
    }
}
