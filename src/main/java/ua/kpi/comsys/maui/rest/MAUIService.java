package ua.kpi.comsys.maui.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.kpi.comsys.maui.domain.ClassID;
import ua.kpi.comsys.maui.domain.Request;
import ua.kpi.comsys.maui.service.RequestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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
//    @Autowired
//    private DefaultFabric defaultFabric;

    @GET
    @Path("/status")
    public Response getSimpleAnswer() {
        String result = "MAUI Rest service is working!\n";
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/requests")
    public Response getRequests() {
        Collection<Request> requests = requestService.getRequests();
        if (requests == null) {
            return Response.status(500).build();
        }
        return Response.status(200).entity((new Gson()).toJson(requests)).build();
    }

    @GET
    @Path("/requestnames")
    public Response getRequestNames() {
        Map<String, String> response = new HashMap<String, String>();
        Collection<Request> requests = requestService.getRequests();
        for (Request request : requests) {
            response.put(request.getName(), request.getId());
        }
        return Response.status(200).entity((new Gson()).toJson(response)).build();
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


    @POST
    @Path("/postrequest")
    public Response save(String jsonRequest) {
        String jsonResponse;
        JsonElement jsonElement = (new Gson()).fromJson(jsonRequest, JsonElement.class).getAsJsonObject().get("request");
        if (jsonElement == null) {
            return Response.status(500).entity("No JSON!").build();
        }
        JsonObject json = jsonElement.getAsJsonObject();
        if (!json.has("type")) {
            return Response.status(500).entity("No type provided!").build();
        }
        if (!json.has("user")) {
            return Response.status(500).entity("No user provided!").build();
        }
        //TODO: bring type to work
//        String type = json.get("type").getAsString();
        String id = UUID.randomUUID().toString();
        String user = json.get("user").getAsString();
        String name = id;
        if(json.has("name")){
            name = json.get("name").getAsString();
        }
        Request request = new Request(id, name, ClassID.SUBMIT_JOB_REQUEST, user);
        LOG.info(request.getId());
        LOG.info(request.getName());
        requestService.save(request);
        jsonResponse = (new Gson()).fromJson("{\"id\":\"" + request.getId() + "\"}", JsonElement.class).toString();
        return Response.ok(jsonResponse).type(MediaType.APPLICATION_JSON).build();
    }

}
