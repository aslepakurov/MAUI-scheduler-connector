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
    @Field("user_id")
    private String user;
    @Field("creation_date")
    private long timeStamp;

    public Request() {
    }

    public Request(String id, String name, ClassID classId, String user_id)  {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.command = classId.getCommand();
        this.user = user_id;
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

    public void setUser(String user_id) {
        this.user = user_id;
    }

    public void setTimeStamp(long creation_date) {
        this.timeStamp = creation_date;
    }
}
