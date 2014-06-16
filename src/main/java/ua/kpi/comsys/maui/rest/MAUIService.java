
package ua.kpi.comsys.maui.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.kpi.comsys.maui.domain.DefaultFabric;
import ua.kpi.comsys.maui.domain.Request;
import ua.kpi.comsys.maui.service.RequestService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MAUIService Class
 *
 * @author Andrew S. Slepakurov
 * @version 28/03/2014
 */
@SuppressWarnings("MethodParameterNamingConvention")
@Path("/maui")
@Component
public class MAUIService {
    private static final Logger LOGGER = Logger.getLogger(MAUIService.class.getName());
    @Autowired
    private RequestService requestService;
    @Autowired
    private DefaultFabric defaultFabric;

    @GET
    @Path("/status")
    public Response getSimpleAnswer() {
        String result = "MAUI Rest service is working!\n";
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/requests")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequests(@QueryParam("id") String id,
                                @QueryParam("name") String name,
                                @QueryParam("user_id") String user_id,
                                @QueryParam("priority") String priority,
                                @QueryParam("status") String status,
                                @QueryParam("sort") String sort,
                                @QueryParam("sortdir") String sortdir) {
        Collection<Request> requests;
        try {
            Query query = new Query();
            if (StringUtils.hasText(id)) {
                query.addCriteria(Criteria.where("id").is(id));
            }
            if (StringUtils.hasText(name)) {
                query.addCriteria(Criteria.where("name").regex(name));
            }
            if (StringUtils.hasText(user_id)) {
                query.addCriteria(Criteria.where("user_id").is(user_id));
            }
            if (StringUtils.hasText(priority)) {
                query.addCriteria(Criteria.where("priority").is(Integer.parseInt(priority)));
            }
            if (StringUtils.hasText(status)) {
                query.addCriteria(Criteria.where("status").is(status.toUpperCase()));
            }
            if (!StringUtils.hasText(sort)) {
                sort = "name";
            }
            if (sort.equals("id")) {
                sort = "_id";
            }
            if (!StringUtils.hasText(sortdir)) {
                sortdir = "asc";
            }
            query.with(new Sort(sortdir.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sort));
            requests = requestService.getRequests(query);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return Response.status(500).build();
        }
        if (requests == null) {
            return Response.status(500).build();
        }
        return Response.status(200).entity((new Gson()).toJson(requests)).build();
    }

    @DELETE
    @Path("/remove/{id}")
    public Response removeRequest(@PathParam("id") String id) {
        try {
            requestService.remove(id);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }


    @POST
    @Path("/request")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonRequest) {
        String jsonResponse;
        JsonElement jsonElement = (new Gson()).fromJson(jsonRequest, JsonElement.class).getAsJsonObject().get("request");
        if (jsonElement == null) {
            return Response.status(500).entity("No JSON!").build();
        }
        JsonObject json = jsonElement.getAsJsonObject();
        if (!json.has("user_id")) {
            return Response.status(500).entity("No user provided!").build();
        }
        Request request;
        if (json.has("id")) {
            request = requestService.getRequest(json.get("id").getAsString());
        } else {
            request = defaultFabric.getDummy();
        }
        if (request == null) {
            return Response.status(500).entity("No request with provided id exists!").build();
        }
        request.setUser(json.get("user_id").getAsString());
        if (!json.has("name") && request.getName() == null) {
            request.setName(request.getId());
        } else {
            request.setName(json.get("name").getAsString());
        }
        if (json.has("priority")) {
            request.setPriority(json.get("priority").getAsInt());
        }
        if (json.has("email")) {
            request.setEmail(json.get("email").getAsString());
        }
        if (json.has("cpu")) {
            request.setCpu(json.get("cpu").getAsInt());
        }
        if (json.has("memory")) {
            request.setMemory(json.get("memory").getAsInt());
        }
        if (json.has("storage")) {
            request.setMemory(json.get("storage").getAsInt());
        }
        if (json.has("node")) {
            request.setNode(json.get("node").getAsInt());
        }
        if (json.has("walltime")) {
            request.setWalltime(json.get("walltime").getAsString());
        }
        if (json.has("storage")) {
            request.setStorage(json.get("storage").getAsInt());
        }
        if (json.has("input_arguments")) {
            request.setInputArguments(json.get("input_arguments").getAsString());
        }
        if (json.has("bash")) {
            request.setBash(json.get("bash").getAsString());
        }
        if (json.has("file_url")) {
            request.setBash(json.get("file_url").getAsString());
        }
        if (json.has("hook_start")) {
            request.setHookStart(json.get("hook_start").getAsString());
        }
        if (json.has("hook_end")) {
            request.setHookEnd(json.get("hook_end").getAsString());
        }
        if (json.has("description")) {
            request.setDescription(json.get("description").getAsString());
        } else {
            request.setDescription(String.format("Name: %s", request.getName()));
        }
        requestService.save(request);
        jsonResponse = (new Gson()).fromJson("{\"id\":\"" + request.getId() + "\"}", JsonElement.class).toString();
        return Response.ok(jsonResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
