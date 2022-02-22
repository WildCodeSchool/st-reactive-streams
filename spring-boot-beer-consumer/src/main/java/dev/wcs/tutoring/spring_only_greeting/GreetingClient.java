package dev.wcs.tutoring.spring_only_greeting;

import reactor.core.publisher.Flux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GreetingClient {

	private final WebClient client;

	// Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
	// We can use it to create a dedicated `WebClient` for our component.
	public GreetingClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:9999").build();
	}

	public Flux<String> getMessage() {
		return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Greeting.class)
				.map(Greeting::getMessage);
	}

}
