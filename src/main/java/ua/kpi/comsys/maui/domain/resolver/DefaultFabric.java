package ua.kpi.comsys.maui.domain.resolver;

import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.domain.Request;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 26/04/14.
 */
@Component
public class DefaultFabric {
    //TODO: move default values to property handler
    private static DefaultFabric instance;

    private DefaultFabric() {
    }

    public static DefaultFabric getInstance() {
        if (instance == null) {
            instance = new DefaultFabric();
        }
        return instance;
    }

    public Request getDefault(String type) {
        Request request = new Request();
        if(type.equalsIgnoreCase("simple")) {
            request.setName("request"+((int) (Math.random())*100000));
            request.setPriority("1");
        }
        return request;
    }
}
