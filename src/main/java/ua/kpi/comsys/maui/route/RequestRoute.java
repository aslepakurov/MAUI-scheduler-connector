package ua.kpi.comsys.maui.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

/**
 * MAUIService Class
 *
 * @author Andrew S. Slepakurov
 * @version 6/17/2014
 */
public class RequestRoute extends RouteBuilder {
    @Value("${rest.port}")
    private String port;
    @Value("${rest.root}")
    private String schema;
    @Override
    public void configure() throws Exception {
        from("cxfrs://bean//mauiREST/").log(LoggingLevel.INFO, "--------!!!!!SUPER WORKING CXF!!!!!--------");
    }
}
