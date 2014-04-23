package ua.kpi.comsys.maui.domain;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 23/04/14.
 */
public enum ParamType {
    SIMPLE("simple"),
    MIDDLE("middle"),
    PRO("pro"),
    FULL("full");

    private String name;

    ParamType(String name) {
        this.name = name;
    }
}
