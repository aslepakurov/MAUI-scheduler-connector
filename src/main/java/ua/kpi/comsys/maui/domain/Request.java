package ua.kpi.comsys.maui.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private String id;
    @Field("name")
    private String name;
    @Field("classId")
    private ClassID classId;
    @Field("command")
    private String command;
    @Field("user")
    private String user;
    @Field("timeStamp")
    private long timeStamp;

    public Request() {
    }

    public Request(String id, String name, ClassID classId, String user)  {
        this.id = id;
        this.name = name;
        this.classId = classId;
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
        return classId;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassID(ClassID classId) {
        this.classId = classId;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
