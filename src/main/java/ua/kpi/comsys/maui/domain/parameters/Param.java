package ua.kpi.comsys.maui.domain.parameters;

import java.util.List;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 23/04/14.
 */
public class Param {
    private String name;
    private ParamType type;
    private List<ParamRestriction> restrictions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParamType getType() {
        return type;
    }

    public void setType(ParamType type) {
        this.type = type;
    }

    public List<ParamRestriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<ParamRestriction> restrictions) {
        this.restrictions = restrictions;
    }
}
