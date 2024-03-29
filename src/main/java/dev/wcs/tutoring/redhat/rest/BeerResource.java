package dev.wcs.tutoring.redhat.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Multi;

@Path("/beers")
public class BeerResource {

    @RestClient
    BeerService beerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Beer> beers() {
        return Multi.createBy().repeating() 
            .supplier( 
                () -> new AtomicInteger(1),
                i -> beerService.getBeers(i.getAndIncrement())
            )
            .until(List::isEmpty) 
            .onItem().<Beer>disjoint() 
            .select().where(b -> b.getAbv() > 6.0);
    }

}
