package ua.kpi.comsys.maui.domain.parameters;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 24/04/14.
 */
public enum ParamRestriction {
    INTEGER("integer"),
    REAL("real"),
    STRING_ONLY("string-only"),
    OVER_ZERO("over_zero");

    private String name;
    private String additional;

    ParamRestriction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
}
