package ua.kpi.comsys.maui;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MediaType;

/**
 * TempClient Class
 *
 * @author Andrew S. Slepakurov
 * @version 29/03/2014
 */
public class TempClient {
    public static void main(String... args) throws JSONException {
        String url = "http://vm-siuesb-d1-smx.dv.kck.ru:8888/rest/maui/postrequest";
        String json = "{\"id\":\"1\",\"priority\":\"BOOOOOYYYYYAAAAAA\"}";
        JSONObject request1 = new JSONObject(json);
        System.out.println(request1);
        Client client = Client.create();
        WebResource r = client.resource(url);
        ClientResponse response = r.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, request1);
        System.out.println(response.getStatus());
    }
}
