package dev.wcs.tutoring;

import dev.wcs.tutoring.quarkus_beer.BeerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		Jsonb jsonb = JsonbBuilder.create();
		BeerClient beerClient = context.getBean(BeerClient.class);
		beerClient.getMessage().subscribe( it -> {
			log.info(jsonb.toJson(it));
		});
	}
}
