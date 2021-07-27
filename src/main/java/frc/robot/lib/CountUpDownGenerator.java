package frc.robot.lib;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CountUpDownGenerator implements Closeable {

    private final AtomicInteger currValue = new AtomicInteger();
    private final AtomicBoolean finished = new AtomicBoolean(false);
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public CountUpDownGenerator(int start, int stop, int pauseMillis) {
        this.executor.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        while (!finished.get()) {
                            for (int i = start; i < stop; i++) {
                                currValue.set(i);
                                if (finished.get())
                                    break;
                                pause(pauseMillis);
                            }

                            for (int i = stop; i > start; i--) {
                                currValue.set(i);
                                if (finished.get())
                                    break;
                                pause(pauseMillis);
                            }
                        }
                    }
                });
    }

    private static void pause(int pauseMillis) {
        try {
            Thread.sleep(pauseMillis);
        } catch (InterruptedException e) {
            // Ignore
        }
    }

    public static void main(String[] args) {
        System.out.println("Waiting 500ms");
        try (CountUpDownGenerator generator = new CountUpDownGenerator(0, 10, 500)) {
            IntStream.range(0, 25).forEach(i -> {
                System.out.println(generator.getValue());
                pause(500);
            });
        }

        System.out.println("No waiting");
        try (CountUpDownGenerator generator = new CountUpDownGenerator(0, 10, 500)) {
            IntStream.range(0, 25).forEach(i -> {
                System.out.println(generator.getValue());
            });
        }

        System.out.println("Waiting 250ms");
        try (CountUpDownGenerator generator = new CountUpDownGenerator(0, 10, 500)) {
            IntStream.range(0, 25).forEach(i -> {
                System.out.println(generator.getValue());
                pause(250);
            });
        }

        System.out.println("Waiting 1000ms");
        try (CountUpDownGenerator generator = new CountUpDownGenerator(0, 10, 500)) {
            IntStream.range(0, 25).forEach(i -> {
                System.out.println(generator.getValue());
                pause(1000);
            });
        }
    }

    public int getValue() {
        return currValue.intValue();
    }

    @Override
    public void close() {
        executor.shutdownNow();
        finished.set(true);
    }
}
