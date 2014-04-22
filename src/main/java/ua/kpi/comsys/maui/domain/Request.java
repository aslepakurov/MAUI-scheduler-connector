package ua.kpi.comsys.maui.domain;

import org.springframework.data.annotation.Id;

/**
 * Request Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
public class Request {
    @Id
    private String id;
    private String priority;
    //TODO: lift me up


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
