package com.pool.threads.race;

public class Counter {
	private long count = 0;

	public long increamntAndGet() {
		synchronized (this) {
			this.count++;
			return this.count;
		}
	}

	public long get() {
		synchronized (this) {
			return this.count;
		}
		
	}
}
