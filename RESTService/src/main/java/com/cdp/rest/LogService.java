package com.cdp.rest;

import com.cdp.model.Logs;
import com.cdp.model.TestLog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("logs")
public class LogService {


    @GET
    @Produces("application/json")
    public String getLogs() {
        return "{'Msg': 'hello word'}";
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveLogsToDB")
    public Response saveLogsToDataBase(ArrayList<TestLog> logs) {
        logs.forEach(System.out::println);
        return Response.ok("Received").build();
    }

}
