# Walkthrough for Quarkus Client/Server Request

![](https://i.imgur.com/V1084bq.png)

## Server-Side Setup

**BeerResource** (Registers REST Controller at http://localhost:8080/beers)

```java
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
```

In this code fragment, the **synchronous** HTTP REST call to the external service is converted to a **asynchronous** stream of data by repeating the synchronous external calls and wrapping them into a Reactive `Multi` object.


**BeerService** (Interface which at runtime will be a Quarkus REST Client)

```java
@Path("/v2")
@RegisterRestClient
public interface BeerService {

    @GET
    @Path("/beers")
    @Produces(MediaType.APPLICATION_JSON)
    List<Beer> getBeers(@QueryParam("page") int page);

}
```

This service call a synchronous external REST Service and is blocking. This blocking reuqest with a _blocking_ data structure `List`. This `List` is used for the static _supplier_ of `Multi` to create a _non-blocking_ `Multi`, which is then used by callers of this method in Reactive, asynchronous style.

## Client-Side Setup

**BeerClient** (This is the REST Client which calls the _internal_ REST API)

_Note: As the `BeerResource` above returns a non-blocking, streaming `Multi`, we have to use a similar non-blocking request on client side. We will use Spring's `Flux` as the equivalent to Quarkus `Multi` for this in the client._

```java
public Flux<Beer> getMessage() {
    return this.client.get().uri("/beers").accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(Beer.class);
}
```

To be able to use the `Flux` response, the caller has to `subscribe` to this `Flux`:

```java
BeerClient beerClient = context.getBean(BeerClient.class);
beerClient.getMessage().subscribe( it -> {
    log.info(jsonb.toJson(it));
});
```

in this case we subscribe and provide a `Consumer`, which logs the response.
