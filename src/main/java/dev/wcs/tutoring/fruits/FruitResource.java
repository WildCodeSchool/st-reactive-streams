package dev.wcs.tutoring.fruits;

import dev.wcs.tutoring.fruits.persistence.Fruit;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {

    @GET
    public Uni<List<Fruit>> get() {
        return Fruit.listAll(Sort.by("name"));
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        return Panache
                .<Fruit>withTransaction(fruit::persist).onItem().transform(inserted -> Response.created(URI.create("/fruits/" + inserted.id)).build());
    }
}
