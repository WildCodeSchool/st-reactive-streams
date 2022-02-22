package dev.wcs.tutoring.quarkus_beer;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class BeerClient {

	private final WebClient client;

	public BeerClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:8080").build();
	}

	public Flux<Beer> getMessage() {
		return this.client.get().uri("/beer").accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Beer.class);
	}

}
