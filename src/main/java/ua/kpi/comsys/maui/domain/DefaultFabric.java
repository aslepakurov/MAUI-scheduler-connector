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
    @Value("${default.memory}")
    private int memory;
    @Value("${default.node}")
    private int node;
    @Value("${default.storage}")
    private int storage;

    public DefaultFabric() {
    }

    public Request getDummy() {
        String id = UUID.randomUUID().toString();
        Request request = new Request(id);
        request.setPriority(priority);
        request.setCpu(cpu);
        request.setMemory(memory);
        request.setNode(node);
        request.setStorage(storage);
        return request;
    }
}
