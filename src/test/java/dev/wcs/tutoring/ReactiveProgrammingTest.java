package dev.wcs.tutoring;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ReactiveProgrammingTest {

    @Test
    public void testMulti() throws InterruptedException {
        List<String> listOf10beers = Arrays.asList("Heineken", "Budweiser", "Corona", "Stella Artois", "Carlsberg", "Bud Light", "Guinness", "Carlsberg", "Budweiser", "Heineken");
        Multi<String> m =
            Multi
                .createFrom().ticks().every(Duration.ofSeconds(1))
                .map(tick -> listOf10beers.get(ThreadLocalRandom.current().nextInt(0, listOf10beers.size())));
        m.subscribe().with(
            item -> System.out.println("Received: " + item)
        );
        for (int i = 0; i < 20; i++) {
            System.out.println("I can do something else!");
            Thread.sleep(200);
        }
    }

    @Test
    public void testUniAndMulti() {
        Multi.createFrom().items("a", "b", "c")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(
                        item -> System.out.println("Received: " + item),
                        failure -> System.out.println("Failed with " + failure)
                );

        Uni.createFrom().item("a")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(
                        item -> System.out.println("Received: " + item),
                        failure -> System.out.println("Failed with " + failure)
                );

    }
}
