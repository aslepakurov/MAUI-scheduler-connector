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
//TODO: a.romas: я его разабстрактил, для работы с БД, как доберешся до реализаций - делай обратно абстрактным
public class Request {

    public static final double DEFAULT_PROTOCOL_VERSION = 0.5;

    @Id
    private final String id;
    private final String name;
    private final ClassID classID;
    private final String command;
    private final String user;
    private final long timeStamp;
    //TODO: same shit here
    public Request(String id, String name, ClassID classId, String user)  {
        this.id = id;
        this.name = name;
        this.classID = classId;
        this.command = classId.getCommand();
        this.user = user;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
