package ua.kpi.comsys.maui.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.domain.Request;
import ua.kpi.comsys.maui.service.RequestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * MAUIService Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Path("/maui")
@Component
public class MAUIService {
//    private static final transient Log LOG = LogFactory.getLog(MAUIService.class);
    @Autowired
    private RequestService requestService;

    @GET
    @Path("/status")
    public Response getSimpleAnswer() {
        String result = "MAUI Rest service is working!\n";
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/requests")
    public Collection<Request> getRequests() {
        return requestService.getRequests();
    }

    @GET
    @Path("/request/{id}")
    public Response getRequest(@PathParam("id") String id) {
        String jsonResponse = (new Gson()).toJson(requestService.getRequest(id));
        return Response.status(200).entity(jsonResponse).build();
    }

    @GET
    @Path("/param/{type}")
    public Response getParam(@PathParam("type") String type) {
        String response = "";
        if(type.equals("simple")) {
            response = "{" +
                        "\"param\":[" +
                                    "{\"name\":\"requestName\"," +
                                    "\"type\":\"text\"," +
                                    "\"restrictions\":[\"string-only\"]}," +
                    "{\"name\":\"priority\"," +
                    "\"type\":\"text\"," +
                    "\"restrictions\":[\"integer\",\"over-zero\"]" +
                    "}" +
                                    "]" +
                        "}";
        }
        String json = (new Gson()).fromJson(response, JsonElement.class).toString();
        return Response.ok(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/postrequest")
    public Response save(String request) {
        JsonObject json = (new Gson()).fromJson(request, JsonElement.class).getAsJsonObject().get("request").getAsJsonObject();
        String id = ""+Math.abs((int)(json.get("requestName").getAsString().hashCode()*Math.random()));
        String name = json.get("requestName").getAsString();
        String priority = json.get("priority").getAsString();
        Request request1 = new Request();
        request1.setId(id);
        request1.setName(name);
        request1.setPriority(priority);
        Logger.getRootLogger().info(request1.getId() + "!!!!!!!!!!!!!!!!!!!!");
        Logger.getRootLogger().info(request1.getName()+"!!!!!!!!!!!!!!!!!!!!");
        Logger.getRootLogger().info(request1.getPriority()+"!!!!!!!!!!!!!!!!!!!!!!!1");
        requestService.save(request1);
        String jsonResponse = (new Gson()).fromJson("{\"id\":\""+request1.getId()+"\"}", JsonElement.class).toString();
        return Response.ok(jsonResponse).type(MediaType.APPLICATION_JSON).build();
    }

}
