package ua.kpi.comsys.maui.domain.parameters;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 23/04/14.
 */
public enum ParameterKind {
    /**
     * The most simple type of parameters set
     */
    SIMPLE("simple"),
    /**
     * The middle type of parameters set
     */
    MIDDLE("middle"),
    /**
     * The professional set of parameters
     */
    PRO("pro"),
    /**
     * Full set of parameters
     */
    FULL("full");

    private String name;

    ParameterKind(String name) {
        this.name = name;
    }
}
