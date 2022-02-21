package dev.wcs.tutoring.redhat.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v2")
@RegisterRestClient
public interface BeerService {

    @GET
    @Path("/beers")
    @Produces(MediaType.APPLICATION_JSON)
    List<Beer> getBeers(@QueryParam("page") int page);

}