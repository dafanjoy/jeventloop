package com.fan.eventLoop.partition;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultPartitioner implements Partitioner {

	private final ConcurrentMap<Integer, AtomicInteger> topicCounterMap = new ConcurrentHashMap<>();
	/**
	 * Compute the partition for the given record.
	 *
	 * @param topic
	 *            The topic name
	 * @param key
	 *            The key to partition on (or null if no key)
	 * @param keyBytes
	 *            serialized key to partition on (or null if no key)
	 * @param value
	 *            The value to partition on or null
	 * @param valueBytes
	 *            serialized value to partition on or null
	 * @param cluster
	 *            The current cluster metadata
	 */
	public int partition(int core, Object key) {
		if (key == null) {
			int nextValue = nextValue(core);
			return Math.abs(nextValue % core);

		} else {
			// hash the keyBytes to choose a partition
			return Math.abs(key.hashCode() % core);
		}
	}

	private int nextValue(int core) {
		AtomicInteger counter = topicCounterMap.get(core);
		if (null == counter) {
			counter = new AtomicInteger(ThreadLocalRandom.current().nextInt());
			AtomicInteger currentCounter = topicCounterMap.putIfAbsent(core, counter);
			if (currentCounter != null) {
				counter = currentCounter;
			}
		}
		return counter.getAndIncrement();
	}

	public void close() {
	}

}