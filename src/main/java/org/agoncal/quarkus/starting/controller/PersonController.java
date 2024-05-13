package org.agoncal.quarkus.starting.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.agoncal.quarkus.starting.dto.CommonListDTO;
import org.agoncal.quarkus.starting.entity.Customer;

@Path("/api/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    @GET
    public CommonListDTO<Customer> getPersons(){
        return null;
    }
}
