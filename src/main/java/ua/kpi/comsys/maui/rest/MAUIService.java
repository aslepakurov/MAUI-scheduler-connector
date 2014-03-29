package ua.kpi.comsys.maui.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.comsys.maui.bean.Request;
import ua.kpi.comsys.maui.service.RequestService;

import javax.ws.rs.*;
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
public class MauiService {
    private static final transient Log LOG = LogFactory.getLog(MauiService.class);
    @Autowired
    private RequestService requestService;

    @GET
    @Path("/status")
    public Response getSimpleAnswer() {
        String result = "MAUI Rest service is working!\n";
        result += Request.class.getName() + " collection " + (requestService.collectionExist() ? "exists." : "does not exist.");
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/requests")
    public Collection<Request> getRequests() {
        return requestService.getRequests();
    }

    @GET
    @Path("/request/{id}")
    public Request getRequest(@PathParam("id") String id) {
        return requestService.getRequest(id);
    }

    @POST
    @Path("/postrequest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(JSONObject request) {
        JsonObject json = (new Gson()).fromJson(request.toString(), JsonObject.class);
        String id = json.get("id").getAsString();
        String priority = json.get("priority").getAsString();
        Request request1 = new Request();
        request1.setId(id);
        request1.setPriority(priority);
        requestService.save(request1);
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(request1.getId()).build();
    }

}
