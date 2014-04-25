package ua.kpi.comsys.maui.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.kpi.comsys.maui.domain.Parameters;
import ua.kpi.comsys.maui.domain.Request;
import ua.kpi.comsys.maui.domain.parameters.ParameterKind;
import ua.kpi.comsys.maui.service.ParamService;
import ua.kpi.comsys.maui.service.RequestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * MAUIService Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@Path("/maui")
@Component
public class MAUIService {
    private static final Logger LOG = Logger.getLogger(MAUIService.class.getName());
    @Autowired
    private RequestService requestService;
    @Autowired
    private ParamService paramService;

    @GET
    @Path("/status")
    public Response getSimpleAnswer() {
        String result = "MAUI Rest service is working!\n";
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/requests")
    public Response getRequests() {
        Collection<Request> request = requestService.getRequests();
        if (request == null) {
            return Response.status(500).build();
        }
        return Response.status(200).entity((new Gson()).toJson(request)).build();
    }

    @GET
    @Path("/request/{id}")
    public Response getRequest(@PathParam("id") String id) {
        Request request = requestService.getRequest(id);
        if (request == null) {
            return Response.status(500).build();
        }
        return Response.status(200).entity((new Gson()).toJson(request)).build();
    }

    @GET
    @Path("/remove/{id}")
    public Response removeRequest(@PathParam("id") String id) {
        requestService.remove(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/param/{type}")
    public Response getParam(@PathParam("type") String type) {
        Parameters param = null;
        if (type.equals("simple")) {
            param = paramService.getParam(ParameterKind.SIMPLE);
        }
        String json = (new Gson()).toJson(param);
        return Response.ok(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/postrequest")
    public Response save(String request) {
        JsonObject json = (new Gson()).fromJson(request, JsonElement.class).getAsJsonObject().get("request").getAsJsonObject();
        String requestName = json.get("requestName").getAsString();
        String id = "" + Math.abs((int) (requestName.hashCode() * Math.random()));
        String priority = json.get("priority").getAsString();
        Request request1 = new Request();
        request1.setId(id);
        request1.setName(requestName);
        request1.setPriority(priority);
        LOG.info(request1.getId());
        LOG.info(request1.getPriority());
        requestService.save(request1);
        String jsonResponse = (new Gson()).fromJson("{\"id\":\"" + request1.getId() + "\"}", JsonElement.class).toString();
        return Response.ok(jsonResponse).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/newpostrequest")
    public Response save1(String request) {
        String jsonResponse;
        JsonObject json = (new Gson()).fromJson(request, JsonElement.class).getAsJsonObject().get("request").getAsJsonObject();
        if (json == null) {
            return Response.status(500).build();
        }
        String type = json.get("type").getAsString();
        if (!StringUtils.hasText(type)) {
            return Response.status(500).build();
        }
        //TODO: make default values abstract fabric
        Request request1 = new Request();
        //TODO: move it to resolver class
        if (type.equals("simple")) {
            //TODO: Reflexion maybe?
            request1.setName(json.get("requestName").getAsString());
            request1.setPriority(json.get("priority").getAsString());
            request1.setId("" + Math.abs((int) (request1.getName().hashCode() * Math.random())));
        }
        LOG.info(request1.getId());
        LOG.info(request1.getPriority());
        requestService.save(request1);
        jsonResponse = (new Gson()).fromJson("{\"id\":\"" + request1.getId() + "\"}", JsonElement.class).toString();
        return Response.ok(jsonResponse).type(MediaType.APPLICATION_JSON).build();
    }

}
