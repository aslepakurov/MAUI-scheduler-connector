package ua.kpi.comsys.maui.domain.parameters;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 24/04/14.
 */
public enum ParamType {
    TEXT("text"),
    DATE("date"),
    RANGE("range"),
    CHECKBOX("checkbox");

    private String name;

    ParamType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}