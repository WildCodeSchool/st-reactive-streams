package dev.wcs.tutoring;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ReactiveProgrammingTest {

    private List<String> listOf10beers;

    @BeforeEach
    public void setup() {
        listOf10beers = Arrays.asList("Heineken", "Budweiser", "Corona", "Stella Artois", "Carlsberg", "Bud Light", "Guinness", "Schorschbock", "Snake Venom", "Tactical Nuclear Penguin");
    }

    @Test
    public void shouldDemoSyncWithBlocking() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            // Sync call to randomBeerService
            // Thread is blocking here
            String randomBeer = getRandomBeer();
            System.out.println(randomBeer);
        }
    }

    private String getRandomBeer() throws InterruptedException {
        // simulate waiting (same functionality as "tick" in async)
        Thread.sleep(1000);
        return listOf10beers.get(ThreadLocalRandom.current().nextInt(0, listOf10beers.size()));
    }

    @Test
    public void shouldDemoAsyncWithoutBlocking() throws InterruptedException {
        // Publisher
        Multi<String> publisher =
            Multi
                .createFrom().ticks().every(Duration.ofSeconds(1))
                .map(tick -> listOf10beers.get(ThreadLocalRandom.current().nextInt(0, listOf10beers.size())));

        // Subscriber
        publisher.subscribe().with(
            item -> System.out.println("Received: " + item)
        );

        // Do something
        for (int i = 0; i < 40; i++) {
            System.out.println("Nice, I am not blocked!");
            Thread.sleep(200);
        }
    }

    @Test
    public void demoUniAndMulti() {
        Multi.createFrom().items("apple", "banana", "peach")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(
                        item -> System.out.println("Received: " + item),
                        failure -> System.out.println("Failed with " + failure)
                );

        Uni.createFrom().item("apple")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(
                        item -> System.out.println("Received: " + item),
                        failure -> System.out.println("Failed with " + failure)
                );
    }
}
