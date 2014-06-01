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
@SuppressWarnings("InstanceVariableNamingConvention")
@Document(collection = "request")
@ToString
@EqualsAndHashCode
public class Request {

    public static final double DEFAULT_PROTOCOL_VERSION = 0.5;

    //TODO: Map fields in camelCase, return and insert in under_score
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("priority")
    private int priority;
    @Field("status")
    private Status status;
    @Field("classId")
    private ClassID classId;
    @Field("command")
    private String command;
    @Field("user_id")
    private String user_id;
    @Field("email")
    private String email;
    @Field("creation_date")
    private long creation_date;

    public Request() {
    }

    public Request(String id, String name, ClassID classId, String user_id, String email, int priority)  {
        this.id = id;
        this.name = name;
        this.status = Status.CREATED;
        this.email = email;
        this.priority = priority;
        this.classId = classId;
        this.command = classId.getCommand();
        this.user_id = user_id;
        this.creation_date = System.currentTimeMillis();
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
        return user_id;
    }

    public int getPriority() {
        return priority;
    }

    public String getEmail() {
        return email;
    }

    public long getTimeStamp() {
        return creation_date;
    }

    public Status getStatus() {
        return status;
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
        this.user_id = user_id;
    }

    public void setTimeStamp(long creation_date) {
        this.creation_date = creation_date;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
