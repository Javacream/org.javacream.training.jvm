package org.javacream.training.monitoring;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleJavaApplication {

	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(() -> System.out.println("working..."), 0, 10, TimeUnit.SECONDS);

	}

}
