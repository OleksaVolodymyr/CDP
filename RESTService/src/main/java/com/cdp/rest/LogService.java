package com.cdp.rest;

import com.cdp.db.dao.LogDAO;
import com.cdp.model.TestLog;
import com.cdp.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response saveLogsToDataBase(List<TestLog> logs) {
        logs.forEach(LogDAO::insertLogs);
        return Response.ok("Received").build();
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response addUser(User user) {
        if (user.getAge() < 0 || user.getAge() > 150)
            return Response.status(422).entity("{\"Msg\": \"User has invalid age\"}").build();
        return Response.ok("Success").build();

    }

}
