package ua.kpi.comsys.maui.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.kpi.comsys.maui.domain.parameters.Param;
import ua.kpi.comsys.maui.domain.parameters.ParameterKind;

import java.util.List;

/**
 * MAUI-scheduler-connector
 * Created by Barabashka on 23/04/14.
 */
@Document(collection = "parameters")
@ToString
@EqualsAndHashCode
public class Parameters {
    @Id
    private ParameterKind kind;
    private List<Param> param;

    public ParameterKind getKind() {
        return kind;
    }

    public void setKind(ParameterKind kind) {
        this.kind = kind;
    }

    public List<Param> getParameters() {
        return param;
    }

    public void setParameters(List<Param> parameters) {
        this.param = parameters;
    }

}
