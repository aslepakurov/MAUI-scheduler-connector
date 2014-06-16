package ua.kpi.comsys.maui.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

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

    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("priority")
    private int priority;
    @Field("status")
    private Status status;
    @Field("description")
    private String description;
    @Field("user_id")
    private String user_id;
    @Field("email")
    private String email;
    @Field("creation_date")
    private long creation_date;
    @Field("cpu")
    private int cpu;
    @Field("memory")
    private int memory;
    @Field("storage")
    private int storage;
    @Field("node")
    private int node;
    @Field("walltime")
    private String walltime;
    @Field("env")
    private Map<String, String> env;
    @Field("input_arguments")
    private String input_arguments;
    @Field("bash")
    private String bash;
    @Field("hook_start")
    private String hook_start;
    @Field("hook_end")
    private String hook_end;

    public Request() {
    }

    public Request(String id)  {
        this.id = id;
        this.status = Status.CREATED;
        this.creation_date = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public int getCpu() {
        return cpu;
    }

    public int getMemory() {
        return memory;
    }

    public int getNode() {
        return node;
    }

    public String getWalltime() {
        return walltime;
    }

    public Map<String, String> getEnv() {
        return env;
    }

    public String getInputArguments() {
        return input_arguments;
    }

    public int getStorage() {
        return storage;
    }

    public String getBash() {
        return bash;
    }

    public String getHookStart() {
        return hook_start;
    }

    public String getHookEnd() {
        return hook_end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setWalltime(String walltime) {
        this.walltime = walltime;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

    public void setInputArguments(String input_arguments) {
        this.input_arguments = input_arguments;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setBash(String bash) {
        this.bash = bash;
    }

    public void setHookStart(String hook_start) {
        this.hook_start = hook_start;
    }

    public void setHookEnd(String hook_end) {
        this.hook_end = hook_end;
    }
}
