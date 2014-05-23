package ua.kpi.comsys.maui.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Request Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Document(collection = "request")
@ToString
@EqualsAndHashCode
public abstract class Request {

    public static final double DEFAULT_PROTOCOL_VERSION = 0.5;

    @Id
    private final ClassID classID;
    private final String command;
    private final String user;
    private final long timeStamp;

    protected Request(ClassID id, String user)  {
        this.classID = id;
        this.command = id.getCommand();
        this.user = user;
        this.timeStamp = System.currentTimeMillis();
    }

    public ClassID getClassID() {
        return classID;
    }

    public String getCommand() {
        return command;
    }

    public String getUser() {
        return user;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
