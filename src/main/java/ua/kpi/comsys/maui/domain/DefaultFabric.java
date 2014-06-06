package ua.kpi.comsys.maui.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * MAUIService Class
 *
 * @author Andrew S. Slepakurov
 * @version 6/7/2014
 */
@Component
public class DefaultFabric {

    @Value("${default.priority}")
    private int priority;
    @Value("${default.cpu}")
    private int cpu;
    @Value("${default.ram}")
    private int ram;
    @Value("${default.node}")
    private int node;

    public static final ClassID SUBMIT_JOB_REQUEST = ClassID.SUBMIT_JOB_REQUEST;

    public DefaultFabric() {
    }

    public Request getDummy(String type) {
        String id = UUID.randomUUID().toString();
        Request request = new Request(id, SUBMIT_JOB_REQUEST);
        request.setPriority(priority);
        request.setCpu(cpu);
        request.setRam(ram);
        request.setNode(node);
        return request;
    }
}
