package ua.kpi.comsys.maui.domain;

/**
 * Created by barabashka on 6/1/2014.
 */
public enum Status {
    CREATED("created"),
    PROCESSING("processing"),
    COMPLETE("complete"),
    FAILED("failed");

    private final String name;

    private Status(String name) {
        this.name = name;
    }
}
