package com.pool.threads.race;

public class RaceConditionClient {
	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread firstCounterThread = new Thread(createCounterThread(counter, "Thread1 final count"));
		Thread secondCounterThread = new Thread(createCounterThread(counter, "Thread2 final count"));
		firstCounterThread.start();
		secondCounterThread.start();
	}

	public static Runnable createCounterThread(Counter counter, String message) {
		return () -> {
			for (int i = 0; i < 1_000_000; i++) {
				counter.increamntAndGet();
			}
			System.out.println(message + ": " + counter.get());
		};
	}
}
