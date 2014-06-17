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
    private String root;
    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:"+port+"/"+root+"/maui/status").log(LoggingLevel.INFO, "--------!!!!!SUPER WORKING CXF!!!!!--------");
    }
}
