package ua.kpi.comsys.maui.rest;

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

    @Autowired
    private RequestService requestService;

    @GET
    @Path("/status")
    public Response getSimpleAnswer() {
        String result = "MAUI Rest service is working!";
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
    @Path("/request")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Request request) {
        requestService.save(request);
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(request.getId()).build();
    }
}
